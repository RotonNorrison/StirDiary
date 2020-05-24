package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import androidx.appcompat.app.AppCompatActivity;

public class DcChooseWay extends AppCompatActivity {

    private RadioGroup radioGroup = null;
    private RadioButton chooseStirWay_radiobtn_duihe, chooseStirWay_radiobtn_tiaohe, chooseStirWay_radiobtn_yaohe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_chooseway);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();

        //选择调制方式事件绑定
        radioGroup = (RadioGroup) findViewById(R.id.chooseStirWay_radioGroup);
        chooseStirWay_radiobtn_duihe = (RadioButton) findViewById(R.id.chooseStirWay_radiobtn_duihe);
        chooseStirWay_radiobtn_tiaohe = (RadioButton) findViewById(R.id.chooseStirWay_radiobtn_tiaohe);
        chooseStirWay_radiobtn_yaohe = (RadioButton) findViewById(R.id.chooseStirWay_radiobtn_yaohe);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.chooseStirWay_radiobtn_tiaohe:
                        creatingDiary.setStirWay(1);
                        break;
                    case R.id.chooseStirWay_radiobtn_duihe:
                        creatingDiary.setStirWay(2);
                        break;
                    case R.id.chooseStirWay_radiobtn_yaohe:
                        creatingDiary.setStirWay(3);
                        break;
                }
            }
        });


        //下一步按钮事件绑定
        Button btn_for_continue_to_creating_final_preview;
        btn_for_continue_to_creating_final_preview =

                findViewById(R.id.chooseStirway_nextbtn);
        btn_for_continue_to_creating_final_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_creating_stir = new Intent(DcChooseWay.this, DcStir.class);
                it_for_creating_stir.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_creating_stir);
                finish();
            }
        });
    }
}
