package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DcBaseWine extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_basewine);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();
        //下一步按钮事件绑定
        Button btn_for_continue_to_add_text;
        btn_for_continue_to_add_text = findViewById(R.id.chooseBase_nextbtn);
        btn_for_continue_to_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_add_text = new Intent(DcBaseWine.this, DcText.class);
                it_for_add_text.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_add_text);
                finish();
            }
        });
    }
}
