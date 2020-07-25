package com.example.exam07_android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button register;
    EditText userid, userpassword, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("회원가입 화면");

        userid = (EditText) findViewById(R.id.userid);
        userpassword = (EditText) findViewById(R.id.userpassword);
        username = (EditText) findViewById(R.id.username);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == register) {
            // 공유 프리퍼런스
            SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String confirmId = userid.getText().toString();
            String confirmPassword = userpassword.getText().toString();
            editor.putString("confirmId", confirmId);
            editor.putString("confirmPassword", confirmPassword);

            editor.commit();

            Toast.makeText(this, "아이디 비밀번호 등록 완료", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}