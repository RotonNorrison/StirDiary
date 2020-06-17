package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class DcChooseWay extends AppCompatActivity {

    private RadioGroup radioGroup = null;
    private ImageView chooseStirWay_radiobtn_duihe, chooseStirWay_radiobtn_tiaohe, chooseStirWay_radiobtn_yaohe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_chooseway);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();

        //选择调制方式事件绑定
        chooseStirWay_radiobtn_duihe = (ImageView) findViewById(R.id.chooseStirWay_radiobtn_duihe);
        chooseStirWay_radiobtn_tiaohe = (ImageView) findViewById(R.id.chooseStirWay_radiobtn_tiaohe);
        chooseStirWay_radiobtn_yaohe = (ImageView) findViewById(R.id.chooseStirWay_radiobtn_yaohe);
        chooseStirWay_radiobtn_tiaohe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setStirWay(1);
                Intent it_for_creating_stir = new Intent(DcChooseWay.this, Dcstir.class);
                it_for_creating_stir.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_creating_stir);
                finish();
            }
        });
        chooseStirWay_radiobtn_duihe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setStirWay(2);
                Intent it_for_creating_stir = new Intent(DcChooseWay.this, DcFinal.class);
                it_for_creating_stir.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_creating_stir);
                finish();
            }
        });
        chooseStirWay_radiobtn_yaohe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setStirWay(3);
                Intent it_for_creating_stir = new Intent(DcChooseWay.this, DcShake.class);
                it_for_creating_stir.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_creating_stir);
                finish();
            }
        });

    }
}
