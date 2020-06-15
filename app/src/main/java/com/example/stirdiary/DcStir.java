package com.example.stirdiary;


import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class DcStir extends AppCompatActivity {
    /*private ScheduledExecutorService scheduledExecutor;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ImageView trial=findViewById(R.id.tries);
            int viewId = msg.what;
            switch (viewId){
                case R.id.tries:
                    ViewGroup.LayoutParams params = trial.getLayoutParams();
                    params.height+=1;
                    trial.setLayoutParams(params);
                    break;
            }
        }
    };
    private void updateAdd(int viewid){
        final int vid =viewid;
        scheduledExecutor= Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=vid;
                handler.sendMessage(msg);
            }
        },0,10, TimeUnit.MILLISECONDS);

    }
    private void stopAdd(){
        if (scheduledExecutor!=null){
            scheduledExecutor.shutdown();
            scheduledExecutor=null;
        }
    }*/
    private int state = 0;

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

        sensorhelper.setOnShakeListener(new SensorMangerHelper.OnShakeListener() {

            @Override
            public void onShake() {
                state += 1;
                bottle.startAnimation(rotateRight);
                if (state >= 15) {
                    Intent it_to_creating_final = new Intent(DcStir.this, DcFinal.class);
                    it_to_creating_final.putExtra("diaryInfo", creatingDiary);
                    startActivity(it_to_creating_final);
                    sensorhelper.stop();
                    finish();
                }
            }
        });



        
/*      trial.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    updateAdd(v.getId());    //手指按下时触发不停的发送消息
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    stopAdd();    //手指抬起时停止发送
                }
                return true;
            }
        });

      trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = trial.getLayoutParams();
                params.height+=20;
                trial.setLayoutParams(params);
            }
        });
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
