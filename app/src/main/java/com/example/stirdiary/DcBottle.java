package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DcBottle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_bottle);
        //下一步按钮事件绑定
        Button btn_for_continue_to_choose_base;
        btn_for_continue_to_choose_base = findViewById(R.id.chooseCup_nextbtn);
        btn_for_continue_to_choose_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_choose_base = new Intent(DcBottle.this, DcBaseWine.class);
                startActivity(it_for_choose_base);
                finish();
            }
        });
    }
}
