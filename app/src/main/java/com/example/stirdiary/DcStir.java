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
                Intent it_for_return_to_main = new Intent(DcStir.this, DcFinal.class);
                startActivity(it_for_return_to_main);
                finish();
            }
        });
    }
}
