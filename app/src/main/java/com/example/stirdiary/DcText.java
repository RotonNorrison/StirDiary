package com.example.stirdiary;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.stirdiary.SentimentClassify;

import java.io.IOException;

public class DcText extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_text);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();
        //下一步按钮事件绑定
        Button btn_for_continue_to_choose_stirway;
        btn_for_continue_to_choose_stirway = findViewById(R.id.creatingAddText_nextbtn);
        btn_for_continue_to_choose_stirway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本内容
                EditText editText =(EditText)findViewById(R.id.creatingAddText_editText);
                String text=editText.getText().toString();
                try {
                    String sentiment = SentimentClassify.sentimentClassify(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                creatingDiary.setText(text);
                //跳转下个界面
                Intent it_for_choose_stirway = new Intent(DcText.this, DcChooseWay.class);
                it_for_choose_stirway.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_choose_stirway);
                finish();
            }
        });
    }
}
