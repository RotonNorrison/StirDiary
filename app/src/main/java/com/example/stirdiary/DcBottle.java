package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DcBottle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_bottle);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();
        //四个酒的按钮事件
        final ImageButton btn_for_choose_bottle1 = findViewById(R.id.chooseBottle_Bottle1_btn);
        final ImageButton btn_for_choose_bottle2 = findViewById(R.id.chooseBottle_Bottle2_btn);
        final ImageButton btn_for_choose_bottle3 = findViewById(R.id.chooseBottle_Bottle3_btn);
        final ImageButton btn_for_choose_bottle4 = findViewById(R.id.chooseBottle_Bottle4_btn);
        btn_for_choose_bottle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setBottle_kind(1);
                btn_for_choose_bottle1.setImageResource(R.drawable.ic_glass1_chosen);
                btn_for_choose_bottle2.setImageResource(R.drawable.ic_glass2);
                btn_for_choose_bottle3.setImageResource(R.drawable.ic_glass3);
                btn_for_choose_bottle4.setImageResource(R.drawable.ic_glass4);
            }
        });

        btn_for_choose_bottle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setBottle_kind(2);
                btn_for_choose_bottle1.setImageResource(R.drawable.ic_glass1);
                btn_for_choose_bottle2.setImageResource(R.drawable.ic_glass2_chosen);
                btn_for_choose_bottle3.setImageResource(R.drawable.ic_glass3);
                btn_for_choose_bottle4.setImageResource(R.drawable.ic_glass4);
            }
        });

        btn_for_choose_bottle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setBottle_kind(3);
                btn_for_choose_bottle1.setImageResource(R.drawable.ic_glass1);
                btn_for_choose_bottle2.setImageResource(R.drawable.ic_glass2);
                btn_for_choose_bottle3.setImageResource(R.drawable.ic_glass3_chosen);
                btn_for_choose_bottle4.setImageResource(R.drawable.ic_glass4);
            }
        });

        btn_for_choose_bottle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDiary.setBottle_kind(4);
                btn_for_choose_bottle1.setImageResource(R.drawable.ic_glass1);
                btn_for_choose_bottle2.setImageResource(R.drawable.ic_glass2);
                btn_for_choose_bottle3.setImageResource(R.drawable.ic_glass3);
                btn_for_choose_bottle4.setImageResource(R.drawable.ic_glass4_chosen);
            }
        });


        //下一步按钮事件绑定
        Button btn_for_continue_to_choose_base;
        btn_for_continue_to_choose_base = findViewById(R.id.chooseBottle_nextbtn);
        btn_for_continue_to_choose_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_choose_base = new Intent(DcBottle.this, DcBaseWine.class);
                it_for_choose_base.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_choose_base);
                finish();
            }
        });
    }
}
