package com.example.stirdiary;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //阅读自己日记——单机酒柜事件
        View btn_for_reading = this.getLayoutInflater().inflate((R.layout.activity_main), null);
        btn_for_reading.findViewById(R.id.homepage_ownDiaryReading);
        btn_for_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_reading = new Intent(MainActivity.this, OwnDiaryReading.class);
                startActivity(it_for_reading);
            }
        });

    }
}
