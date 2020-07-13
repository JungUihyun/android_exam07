package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button closeRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("회원가입 화면");

        closeRegister = (Button)findViewById(R.id.closeRegister);
        closeRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == closeRegister) {
            Toast.makeText(getApplicationContext(), "닫기", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
