package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class DiaryCreate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_page);
        final Diary creatingDiary=new Diary();

        //开始调酒按钮事件绑定
        Button btn_for_start;
        btn_for_start = findViewById(R.id.diaryCreation_startbtn);
        btn_for_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_create1 = new Intent(DiaryCreate.this, DcBottle.class);
                it_for_create1.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_create1);
                finish();
            }
        });
    }
}
