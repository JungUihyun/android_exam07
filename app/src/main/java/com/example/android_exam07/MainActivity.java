package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button goRegister, login;
    EditText userid, userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인 화면");

//        로그인 처리
        userid = (EditText)findViewById(R.id.userid);
        userpassword = (EditText)findViewById(R.id.userpassword);
        login = (Button)findViewById(R.id.login);



//        회원가입 화면 이동
        goRegister = (Button)findViewById(R.id.goRegister);
        goRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == goRegister) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }
    }
}
