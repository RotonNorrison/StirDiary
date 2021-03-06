package com.example.stirdiary;

import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Diary> diaryList = new ArrayList<Diary>();
    private DBDiaryDao mdbdao = new DBDiaryDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteStudioService.instance().start(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        diaryList = mdbdao.selectBetweenDate("2020-01-01", "2020-07-30");
        DiaryThumbnailHelper mDTH = new DiaryThumbnailHelper(getApplicationContext());
        Diary diary;
        for (int i = 0; i < diaryList.size(); i++) {
            diary = diaryList.get(i);
            System.out.println(diary.getUid());
            System.out.println(diary.getWinelist().get(1).getWinename());
            try {
                mDTH.generateDiarySVG(diary.getUid(), diary);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //!!!!!!!!!Danger!!!!!!!!
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //创建日记文件保存类
        DiaryFileHelper mFileHelper = new DiaryFileHelper(this);

        //打开应用时进入登录界面
        Intent it_for_login = new Intent(MainActivity.this, LoginPage.class);
        startActivity(it_for_login);
        //用户信息管理按钮事件绑定
        ImageView btn_for_userInfo;
        btn_for_userInfo = findViewById(R.id.activity_main_bartender);
        btn_for_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_reading = new Intent(MainActivity.this, UserInfo.class);
                startActivity(it_for_reading);
            }
        });
        //写日记按钮事件绑定
        ImageView btn_for_creation;
        btn_for_creation = findViewById(R.id.activity_main_tools);
        btn_for_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_creation = new Intent(MainActivity.this, DcBottle.class);
                startActivity(it_for_creation);
            }

        });
        //读日记按钮事件绑定
        ImageView btn_for_reading;
        btn_for_reading = findViewById(R.id.activity_main_bakground);
        btn_for_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_reading = new Intent(MainActivity.this, OwnDiary_list.class);
                startActivity(it_for_reading);
            }
        });

    }
}
