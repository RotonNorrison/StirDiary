package com.example.stirdiary;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //打开应用时进入登录界面
        Intent it_for_login = new Intent(MainActivity.this, LoginPage.class);
        startActivity(it_for_login);
        //用户信息管理按钮事件绑定
        Button btn_for_userInfo;
        btn_for_userInfo = findViewById(R.id.homepage_userInfoManagement);
        btn_for_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_reading = new Intent(MainActivity.this, UserInfo.class);
                startActivity(it_for_reading);
            }
        });
        //写日记按钮事件绑定
        Button btn_for_creation;
        btn_for_creation = findViewById(R.id.homepage_diaryCreation);
        btn_for_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_creation = new Intent(MainActivity.this, DiaryCreate.class);
                startActivity(it_for_creation);
            }

        });
        //读日记按钮事件绑定
        Button btn_for_reading;
        btn_for_reading = findViewById(R.id.homepage_ownDiaryReading);
        btn_for_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_reading = new Intent(MainActivity.this, OwnDiary.class);
                startActivity(it_for_reading);
            }
        });

    }
}
