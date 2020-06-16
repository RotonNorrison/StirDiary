package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import com.example.stirdiary.DBDiaryDao;
import com.example.stirdiary.UUIDGenerator;

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
                if(isChecked)
                    creatingDiary.setShare_state(1);
                else
                    creatingDiary.setShare_state(0);
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

                DBDiaryDao dbDiaryDao=new DBDiaryDao(DcFinal.this);
                dbDiaryDao.insert(creatingDiary);
                Intent it_for_choose_stirway = new Intent(DcFinal.this, MainActivity.class);
                startActivity(it_for_choose_stirway);
                finish();
            }
        });
    }
}
