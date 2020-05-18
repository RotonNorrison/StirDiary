package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DcChooseWay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_creation_chooseway);
        //下一步按钮事件绑定
        Button btn_for_continue_to_creating_final_preview;
        btn_for_continue_to_creating_final_preview = findViewById(R.id.chooseStirway_nextbtn);
        btn_for_continue_to_creating_final_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_creating_final_preview = new Intent(DcChooseWay.this, DcStir.class);
                startActivity(it_for_creating_final_preview);
                finish();
            }
        });
    }
}
