package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class DcFinal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_final);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");

        //分享按钮事件绑定
        ToggleButton sharebtn;
        sharebtn=findViewById(R.id.creatingFinal_sharebtn);
        sharebtn.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    creatingDiary.setShare_state(isChecked);
            }
        });

        //下一步按钮事件绑定
        Button btn_for_end_creating;
        btn_for_end_creating = findViewById(R.id.creatingFinal_endbtn);
        btn_for_end_creating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本内容
                EditText text = (EditText) findViewById(R.id.creatingFinal_addTitle_editText);
                String title = text.getText().toString();
                creatingDiary.setDiary_title(title);
                creatingDiary.showInfo();
                Intent it_for_choose_stirway = new Intent(DcFinal.this, MainActivity.class);
                startActivity(it_for_choose_stirway);
                finish();
            }
        });
    }
}
