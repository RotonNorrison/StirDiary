package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DcChooseWay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_chooseway);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();


        //选择调制方式事件绑定
        ImageButton btn_for_choose_stir_way1 = findViewById(R.id.creatingStir_stirway1btn);
        btn_for_choose_stir_way1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setStirWay(1);
            }
        });
        ImageButton btn_for_choose_stir_way2 = findViewById(R.id.creatingStir_stirway2btn);
        btn_for_choose_stir_way2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setStirWay(2);
            }
        });
        ImageButton btn_for_choose_stir_way3 = findViewById(R.id.creatingStir_stirway3btn);
        btn_for_choose_stir_way3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setStirWay(3);
            }
        });


        //下一步按钮事件绑定
        Button btn_for_continue_to_creating_final_preview;
        btn_for_continue_to_creating_final_preview = findViewById(R.id.chooseStirway_nextbtn);
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
