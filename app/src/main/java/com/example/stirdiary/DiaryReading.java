package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
public class DiaryReading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_reading_page);
        //创建文件辅助类
        DiaryFileHelper mFileHelper = new DiaryFileHelper(getApplicationContext());
        Diary myDiary = new Diary();
        String content;

        //获得日记内容
        Intent it = getIntent();
        myDiary = (Diary) it.getSerializableExtra("file");
        content = myDiary.getText();
        TextView paper = findViewById(R.id.diary_reading_page_textPaper);
        paper.setText(content);
    }
}