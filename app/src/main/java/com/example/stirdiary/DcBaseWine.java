package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.Pair;
import androidx.appcompat.app.AppCompatActivity;

public class DcBaseWine extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_basewine);

        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();

        final RadioGroup radioGroupWine = findViewById(R.id.radio_group_wine);
        final RadioGroup radioGroupJuice = findViewById(R.id.radio_group_juice);

        final String[] winename = new String[1];
        final Double[] volume = new Double[1];
        //图片生成
        final DiaryFileHelper mDFH = new DiaryFileHelper(getApplicationContext());

        //酒添加
        final SeekBar wineSeekBar = findViewById(R.id.chooseBase_wine_amount_seekbar);
        final TextView wineAmountText = findViewById(R.id.chooseBase_wine_amount_text);
        final Button addWineButton = findViewById(R.id.chooseBase_wine_addbtn);
        wineSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wineAmountText.setText(Integer.toString(progress) + "ml");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        addWineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = radioGroupWine.getChildCount();
                for(int i = 0 ;i < count;i++){
                    RadioButton rb = (RadioButton)radioGroupWine.getChildAt(i);
                    if(rb.isChecked()){
                        System.out.println(i);
                        winename[0]=rb.getTag().toString();
                        break;
                    }
                }
//                //判定选定的wine
//                radioGroupWine.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//                    @Override
//                    publicc void onCheckedChanged(RadioGroup group, int checkedId) {
//                        int id=group.getCheckedRadioButtonId();
//                        switch (id){
//                            case  R.id.radioButton_brandy:
//                                winename[0] ="brandy";
//                                break;
//                            case  R.id.radioButton_gin:
//                                winename[0] ="gin";
//                                break;
//                            case  R.id.radioButton_rum:
//                                winename[0] ="rum";
//                                break;
//                            case  R.id.radioButton_tequila:
//                                winename[0] ="tequila";
//                                break;
//                            case  R.id.radioButton_vodka:
//                                winename[0] ="vodka";
//                                break;
//                            case R.id.radioButton_whisky:
//                                winename[0]="whisky";
//                                break;
//                        }
//                    }
//                });

                //判定数量
                volume[0] = (double) wineSeekBar.getProgress();

                //加入winelist
                creatingDiary.addWine(winename[0], volume[0]);
                try {
                    mDFH.generateDiarySVG("temp", creatingDiary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//果汁添加
        final SeekBar juiceSeekBar = findViewById(R.id.chooseBase_juice_amount_seekbar);
        final TextView juiceAmountText = findViewById(R.id.chooseBase_juice_amount_text);
        final Button addJuiceButton=findViewById(R.id.chooseBase_juice_addbtn);
        juiceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                juiceAmountText.setText(Integer.toString(progress) + "ml");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        addJuiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = radioGroupJuice.getChildCount();
                for(int i = 0 ;i < count;i++){
                    RadioButton rb = (RadioButton)radioGroupJuice.getChildAt(i);
                    if(rb.isChecked()){
                        winename[0]=rb.getTag().toString();
                        break;
                    }
                }
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

                //判定数量
                volume[0] = (double) juiceSeekBar.getProgress();

                //加入winelist
                creatingDiary.addWine(winename[0], volume[0]);
                try {
                    mDFH.generateDiarySVG("temp", creatingDiary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //下一步按钮事件绑定
        Button btn_for_continue_to_add_text;
        btn_for_continue_to_add_text = findViewById(R.id.chooseBase_nextbtn);
        btn_for_continue_to_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_add_text = new Intent(DcBaseWine.this, DcText.class);
                it_for_add_text.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_add_text);
                finish();
            }
        });
    }
}
