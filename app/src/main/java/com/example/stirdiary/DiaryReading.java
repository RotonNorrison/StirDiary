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
        DiaryFileHelper mFileHelper = new DiaryFileHelper(this);
        Diary myDiary = new Diary();
        String name;
        String content;

        //获得日记内容
        Intent it = getIntent();
        name = it.getStringExtra("filename");
        try {
            myDiary = mFileHelper.readDiaryFromFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = myDiary.getText();
        TextView paper = findViewById(R.id.diary_reading_page_textPaper);
        paper.setText(content);
    }
}