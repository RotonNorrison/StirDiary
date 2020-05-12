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
        //读日记事件绑定
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
