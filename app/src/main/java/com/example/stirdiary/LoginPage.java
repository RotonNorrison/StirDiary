package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_page);
        //登录按钮事件绑定
        Button btn_for_login;
        btn_for_login = findViewById(R.id.login_loginbtn);
        btn_for_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //注册按钮事件绑定
        Button btn_for_register;
        btn_for_register = findViewById(R.id.login_registerbtn);
        btn_for_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_for_register = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(it_for_register);
                finish();
            }
        });
    }
}
