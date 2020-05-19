package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DiaryReading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String content;
        super.onCreate(savedInstanceState);
        //Intent it=getIntent();
        //content=it.getStringExtra("src");
        setContentView(R.layout.diary_reading_page);
        TextView paper = findViewById(R.id.diary_reading_page_textPaper);
        paper.setText("content");
        paper.setBackgroundResource(R.drawable.background);
    }
}