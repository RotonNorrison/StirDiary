package com.example.stirdiary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.Pair;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DcJuice extends AppCompatActivity {
    private Diary creatingDiary;
    private ConstraintLayout mContainer;
    private ScheduledExecutorService scheduledExecutor;
    private List<Wine> wineList = new ArrayList<Wine>();
    private final int[] curWine = {0};
    private int curHeight = 0;
    private int formerHeight = 0;
    private boolean onAdding = false;
    private int curId = 1000;
    final ConstraintSet set = new ConstraintSet();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (curHeight > 1550) {
                return;
            }
            if (!onAdding) {
                curId += 1;

                SinWaveView swView = new SinWaveView(DcJuice.this);
                swView.setId(curId);
                swView.setHeight(curHeight);
                swView.changeColor(wineList.get(curWine[0]).color);


                mContainer.addView(swView, 1);
                set.clone(mContainer);
                set.connect(curId, ConstraintSet.LEFT, R.id.chooseJuice_background, ConstraintSet.LEFT);
                set.connect(curId, ConstraintSet.RIGHT, R.id.chooseJuice_background, ConstraintSet.RIGHT);
                set.connect(curId, ConstraintSet.END, R.id.chooseJuice_background, ConstraintSet.END);
                set.applyTo(mContainer);
                onAdding = !onAdding;

            }
            SinWaveView trial = findViewById(curId);

            trial.addHeight();
            curHeight += 1;

        }
    };

    private void updateAdd(int viewid) {
        final int vid = viewid;
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = vid;
                handler.sendMessage(msg);
            }
        }, 0, 8, TimeUnit.MILLISECONDS);

    }

    private void stopAdd() {
        if (scheduledExecutor != null) {
            scheduledExecutor.shutdown();
            scheduledExecutor = null;
        }
    }

    private void wine_init() {
        Wine brandy_wine = new Wine("Grenadine: ", 0xffED515C, 1000);
        Wine gin_wine = new Wine("Orange: ", 0xffFBB571, 1001);
        Wine rum_wine = new Wine("Cherry: ", 0xffFF92FF, 1002);
        Wine tequila_wine = new Wine("Lime: ", 0xff6AFFAB, 1003);
        Wine vodka_wine = new Wine("TripleSec: ", 0xff00FFFF, 1004);
        Wine whisky_wine = new Wine("Blackberry: ", 0xffC11EF1, 1005);
        wineList.add(brandy_wine);
        wineList.add(gin_wine);
        wineList.add(rum_wine);
        wineList.add(tequila_wine);
        wineList.add(vodka_wine);
        wineList.add(whisky_wine);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_juice);
        wine_init();
        creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();

        final RadioGroup radioGroupWine = findViewById(R.id.chooseBase_RadioGroup);
        final RadioButton radioButtonBrandy = findViewById(R.id.radioButton_brandy);
        final RadioButton radioButtonGin = findViewById(R.id.radioButton_gin);
        final RadioButton radioButtonRum = findViewById(R.id.radioButton_rum);
        final RadioButton radioButtonTequila = findViewById(R.id.radioButton_tequila);
        final RadioButton radioButtonVodka = findViewById(R.id.radioButton_vodka);
        final RadioButton radioButtonWhisky = findViewById(R.id.radioButton_whisky);

        final String[] winename = new String[1];
        final Double[] volume = new Double[1];
        //根据选定的酒改变背景颜色
        final ConstraintLayout background0 = findViewById(R.id.chooseJuice_background);
        radioGroupWine.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.radioButton_brandy:
                        background0.setBackgroundColor(Color.parseColor("#ED9C9B"));

                        onAdding = false;
                        if (curHeight != 0) {
                            creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                            formerHeight = curHeight;
                            creatingDiary.showInfo();
                        }
                        curWine[0] = 0;
                        break;
                    case R.id.radioButton_gin:
                        background0.setBackgroundColor(Color.parseColor("#FBCD8C"));

                        onAdding = false;
                        if (curHeight != 0) {
                            creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                            formerHeight = curHeight;
                            creatingDiary.showInfo();
                        }
                        curWine[0] = 1;
                        break;
                    case R.id.radioButton_rum:
                        background0.setBackgroundColor(Color.parseColor("#FFB9FF"));

                        if (curHeight != 0) {
                            creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                            formerHeight = curHeight;
                            creatingDiary.showInfo();
                        }
                        curWine[0] = 2;
                        onAdding = false;
                        break;
                    case R.id.radioButton_tequila:
                        background0.setBackgroundColor(Color.parseColor("#82FFC9"));

                        onAdding = false;
                        if (curHeight != 0) {
                            creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                            formerHeight = curHeight;
                            creatingDiary.showInfo();
                        }
                        curWine[0] = 3;
                        break;
                    case R.id.radioButton_vodka:
                        background0.setBackgroundColor(Color.parseColor("#B0FFFF"));

                        onAdding = false;
                        if (curHeight != 0) {
                            creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                            formerHeight = curHeight;
                            creatingDiary.showInfo();
                        }
                        curWine[0] = 4;
                        break;
                    case R.id.radioButton_whisky:
                        background0.setBackgroundColor(Color.parseColor("#C1AAF1"));

                        if (curHeight != 0) {
                            creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                            formerHeight = curHeight;
                            creatingDiary.showInfo();
                        }
                        curWine[0] = 5;
                        onAdding = false;
                        break;
                }
            }
        });
        //酒添加

        mContainer = findViewById(R.id.chooseJuice_background);


        final Button addWineButton = findViewById(R.id.chooseBase_wine_addbtn);
        addWineButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    updateAdd(curId);    //手指按下时触发不停的发送消息
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopAdd();    //手指抬起时停止发送
                }
                return true;
            }
        });


        Button btn_for_continue_to_add_text;
        btn_for_continue_to_add_text = findViewById(R.id.chooseBase_nextbtn);
        btn_for_continue_to_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdding & curHeight != 0) {
                    creatingDiary.addWine(curWine[0] + 6, (curHeight - formerHeight));
                    formerHeight = curHeight;
                    creatingDiary.showInfo();
                }
                Intent it_for_add_text = new Intent(DcJuice.this, DcText.class);
                it_for_add_text.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_add_text);
                finish();
            }
        });
    }
}


//果汁添加

//                //判定选定的juice
//                radioGroupJuice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//                    @Override
//                    publicc void onCheckedChanged(RadioGroup group, int checkedId) {
//                        int id=group.getCheckedRadioButtonId();
//                        switch (id){
//                            case  R.id.radioButton_blackberry:
//                                winename[0] ="blackberry";
//                                break;
//                            case  R.id.radioButton_cherry:
//                                winename[0] ="cherry";
//                                break;
//                            case  R.id.radioButton_lime:
//                                winename[0] ="lime";
//                                break;
//                            case  R.id.radioButton_orange:
//                                winename[0] ="orange";
//                                break;
//                            case  R.id.radioButton_triple_sec:
//                                winename[0] ="triple_sec";
//                                break;
//                        }
//                    }
//                });


//下一步按钮事件绑定

