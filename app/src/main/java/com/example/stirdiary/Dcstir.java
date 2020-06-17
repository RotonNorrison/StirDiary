package com.example.stirdiary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class Dcstir extends AppCompatActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ConstraintLayout mLayout = findViewById(R.id.stirlayout);
            Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
            mLayout.setBackgroundColor(0xff79FF96);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creating_stir);

        final ConstraintLayout mLayout = findViewById(R.id.stirlayout);
        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();

        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent it_to_creating_final = new Intent(Dcstir.this, DcFinal.class);
                it_to_creating_final.putExtra("diaryInfo", creatingDiary);
                startActivity(it_to_creating_final);
                finish();
            }
        };
        RoundProgressView mRView = findViewById(R.id.stirplace);
        mRView.setOnAchievedListener(new RoundProgressView.OnAchievedListener() {
            @Override
            public void achieved() {
                Message mes = new Message();
                handler.sendMessage(mes);
                timer.schedule(task, 1000);
            }
        });
    }

}
