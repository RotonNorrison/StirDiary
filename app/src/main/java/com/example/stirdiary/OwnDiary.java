package com.example.stirdiary;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OwnDiary extends AppCompatActivity {
    private LinearLayout container;

    public void addWine(int num) {
        for (int i = 0; i < num; i++) {
            container = findViewById(R.id.container1);
            DiaryView child
            child.setText("123");
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
