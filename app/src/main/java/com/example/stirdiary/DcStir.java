package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DcStir extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_stir);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();
        //下一步按钮事件绑定
        Button btn_for_return_to_main;
        btn_for_return_to_main = findViewById(R.id.creatingStir_nextbtn);
        btn_for_return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_to_creating_final = new Intent(DcStir.this, DcFinal.class);
                it_to_creating_final.putExtra("diaryInfo", creatingDiary);
                startActivity(it_to_creating_final);
                finish();
            }
        });
    }
}
