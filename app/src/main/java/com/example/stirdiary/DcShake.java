package com.example.stirdiary;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class DcShake extends AppCompatActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ConstraintLayout mLayout = findViewById(R.id.shakelayout);
            Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
            mLayout.setBackgroundColor(Color.GREEN);

        }
    };
    /**/
    private int state = 0;
    private boolean notstop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_shake);

        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();

        final ImageView bottle = findViewById(R.id.bottle);
        final Animation rotateRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        LinearInterpolator lin1 = new LinearInterpolator();
        rotateRight.setInterpolator(lin1);

        final SensorMangerHelper sensorhelper = new SensorMangerHelper(this);

        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent it_to_creating_final = new Intent(DcShake.this, DcFinal.class);
                it_to_creating_final.putExtra("diaryInfo", creatingDiary);
                startActivity(it_to_creating_final);
                finish();
            }
        };
        sensorhelper.setOnShakeListener(new SensorMangerHelper.OnShakeListener() {

            @Override
            public void onShake() {
                state += 1;
                bottle.startAnimation(rotateRight);
                if (state >= 15 & notstop) {
                    notstop = false;
                    Message mes = new Message();
                    handler.sendMessage(mes);
                    timer.schedule(task, 1000);
                }
            }
        });




        /*
         */


        //下一步按钮事件绑定
       /* Button btn_for_return_to_main;
        btn_for_return_to_main = findViewById(R.id.creatingStir_nextbtn);
        btn_for_return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_to_creating_final = new Intent(DcStir.this, DcFinal.class);
                it_to_creating_final.putExtra("diaryInfo", creatingDiary);
                startActivity(it_to_creating_final);
                finish();
            }
        });*/
    }
}
