package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DcFinal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_final);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        final DiaryFileHelper mDFH = new DiaryFileHelper(getApplicationContext());
        List<Diary> Dlist = null;
        try {
            Dlist = mDFH.readDiaryListFromFile("diary_list_storage");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Dlist == null) {
            Dlist = new ArrayList<Diary>();
        }
        //分享按钮事件绑定
        ToggleButton sharebtn;
        sharebtn = findViewById(R.id.creatingFinal_sharebtn);
        sharebtn.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                creatingDiary.setShare_state(isChecked);
            }
        });

        //下一步按钮事件绑定
        Button btn_for_end_creating;
        btn_for_end_creating = findViewById(R.id.creatingFinal_endbtn);
        final List<Diary> finalDlist = Dlist;
        btn_for_end_creating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本内容
                EditText text = (EditText) findViewById(R.id.creatingFinal_addTitle_editText);
                String title = text.getText().toString();
                creatingDiary.setDiary_title(title);
                creatingDiary.showInfo();
                finalDlist.add(creatingDiary);
                try {
                    mDFH.saveDiaryListToFile("diary_list_storage", finalDlist);
                    mDFH.generateDiarySVG(creatingDiary.getDiary_title(), creatingDiary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
