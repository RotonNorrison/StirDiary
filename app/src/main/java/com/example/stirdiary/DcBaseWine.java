package com.example.stirdiary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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

public class DcBaseWine extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_basewine);

        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
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
        final ConstraintLayout background0 = findViewById(R.id.chooseBase_background);
        final ImageView background1 = findViewById(R.id.chooseBase_img_background1);
        radioGroupWine.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.radioButton_brandy:
                        background0.setBackgroundColor(Color.parseColor("#c7b299"));
                        //background1.setBackgroundColor(Color.parseColor("#82272d"));
                        break;
                    case R.id.radioButton_gin:
                        background0.setBackgroundColor(Color.parseColor("#c92700"));
                        //background1.setBackgroundColor(Color.parseColor("#f2f2f2"));
                        break;
                    case R.id.radioButton_rum:
                        background0.setBackgroundColor(Color.parseColor("#fbb097"));
                        //background1.setBackgroundColor(Color.parseColor("#f18258"));
                        break;
                    case R.id.radioButton_tequila:
                        background0.setBackgroundColor(Color.parseColor("#b5e7ad"));
                        //background1.setBackgroundColor(Color.parseColor("#fffb85"));
                        break;
                    case R.id.radioButton_vodka:
                        background0.setBackgroundColor(Color.parseColor("#29abe2"));
                        //background1.setBackgroundColor(Color.parseColor("#f2f2f2"));
                        break;
                    case R.id.radioButton_whisky:
                        background0.setBackgroundColor(Color.parseColor("#730028"));
                        //background1.setBackgroundColor(Color.parseColor("#f17324"));
                        break;
                }
            }
        });
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
                for (int i = 0; i < count; i++) {
                    RadioButton rb = (RadioButton) radioGroupWine.getChildAt(i);
                    if (rb.isChecked()) {
                        System.out.println(i);
                        winename[0] = rb.getTag().toString();
                        break;
                    }
                }
            }
        });
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

