package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button goRegister, login;
    EditText userid, userpassword;

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인 화면");

//        로그인 처리
        userid = (EditText)findViewById(R.id.userid);
        userpassword = (EditText)findViewById(R.id.userpassword);
        login = (Button)findViewById(R.id.login);

        helper = new DatabaseOpenHelper(MainActivity.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        login.setOnClickListener(this);

//        회원가입 화면 이동
        goRegister = (Button)findViewById(R.id.goRegister);
        goRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == goRegister) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        } else if(view == login) {
            String id = userid.getText().toString();
            String password = userpassword.getText().toString();

            if(id.length() == 0 || password.length() == 0) {
                // 아이디와 비밀번호는 필수 입력사항입니다.
                Toast.makeText(MainActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            sql = "SELECT id FROM " + helper.tableName + " WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);

            if(cursor.getCount() != 1) {
                // 아이디가 틀렸습니다.
                Toast.makeText(MainActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            sql = "SELECT password FROM "+ helper.tableName + " WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);

            cursor.moveToNext();
            if(!password.equals(cursor.getString(0))){
                //비밀번호가 틀렸습니다.
                Toast toast = Toast.makeText(MainActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                //로그인성공
                Toast toast = Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT);
                toast.show();

                //인텐트 생성 및 호출
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                finish();
            }
            cursor.close();
        }
    }
}
















