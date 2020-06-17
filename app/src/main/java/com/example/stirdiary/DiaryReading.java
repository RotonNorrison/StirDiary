package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
public class DiaryReading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_reading_page);

        //创建文件辅助类
        Diary myDiary = new Diary();
        //获得日记内容
        Intent it = getIntent();
        myDiary = (Diary) it.getSerializableExtra("file");
        TextView paper = findViewById(R.id.diary_reading_page_textPaper);
        paper.setText(myDiary.getText());
        TextView title = findViewById(R.id.reading_title);
        title.setText(myDiary.getDiary_title());
    }
}