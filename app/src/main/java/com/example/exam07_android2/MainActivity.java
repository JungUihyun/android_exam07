package com.example.exam07_android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button goRegister, login;
    EditText  userid, userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인 화면");

        // 로그인 처리
        userid = (EditText)findViewById(R.id.userid);
        userpassword = (EditText)findViewById(R.id.userpassword);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(this);

        // 회원가입 화면 이동
        goRegister = (Button)findViewById(R.id.goRegister);
        goRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == goRegister) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        } else if(v == login) {
            String id = userid.getText().toString();
            String password = userpassword.getText().toString();

            //  공유프리퍼런스
            SharedPreferences sf = getSharedPreferences("sFile", MODE_PRIVATE);
            String confirmId = sf.getString("confirmId", "");
            String confirmPassword = sf.getString("confirmPassword", "");

            if(id.length() == 0 || password.length() == 0) {
                // 아이디와 비밀번호는 필수 입력사항입니다.
                Toast.makeText(MainActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(id.equals(confirmId) && password.equals(confirmPassword)) {
                Toast.makeText(this, "저장된 데이터와 동일한 값", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userId", id);
                editor.putString("userPassword", password);
                editor.putString("userName", "test");

                //인텐트 생성 및 호출
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }
}