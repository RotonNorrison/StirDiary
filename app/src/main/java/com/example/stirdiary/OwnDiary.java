package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OwnDiary extends AppCompatActivity {
    private LinearLayout container;

    public void addWine(int num) {
        String imgsrc;
        final String contentsrc;
        contentsrc = "test";
        for (int i = 0; i < num; i++) {
            container = findViewById(R.id.container1);
            ImageView child = new ImageView(this);
            child.setImageResource(R.drawable.diaryicon);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 400);
            params.setMargins(30, 30, 30, 30);
            child.setLayoutParams(params);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(OwnDiary.this, DiaryReading.class);
                    it.putExtra("filename", contentsrc);
                    startActivity(it);
                }
            });
            container.addView(child);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_diary_page);
        addWine(3);
    }
}
