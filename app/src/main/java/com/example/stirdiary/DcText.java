package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DcText extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_text);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();
        //图片生成
        final DiaryFileHelper mDFH = new DiaryFileHelper(getApplicationContext());

        //下一步按钮事件绑定
        Button btn_for_continue_to_choose_stirway;
        btn_for_continue_to_choose_stirway = findViewById(R.id.creatingAddText_nextbtn);
        btn_for_continue_to_choose_stirway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本内容
                EditText editText = (EditText) findViewById(R.id.creatingAddText_editText);
                String text = editText.getText().toString();
                creatingDiary.setText(text);
//                try {
//                    mDFH.generateDiarySVG("temp", creatingDiary);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                //跳转下个界面
                Intent it_for_choose_stirway = new Intent(DcText.this, DcChooseWay.class);
                it_for_choose_stirway.putExtra("diaryInfo", creatingDiary);
                startActivity(it_for_choose_stirway);
                finish();
            }
        });
    }
}
