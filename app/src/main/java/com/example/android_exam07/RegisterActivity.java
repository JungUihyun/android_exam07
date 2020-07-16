package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

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

        helper = new DatabaseOpenHelper(RegisterActivity.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == register) {
            String id = userid.getText().toString();
            String password = userpassword.getText().toString();

            if(id.length() == 0 || password.length() == 0) {
                //아이디와 비밀번호는 필수 입력사항입니다.
                Toast toast = Toast.makeText(RegisterActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            sql = "SELECT id FROM "+ helper.tableName + " WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);

            if(cursor.getCount() != 0){
                //존재하는 아이디입니다.
                Toast toast = Toast.makeText(RegisterActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                helper.insertUser(database,id,password);
                Toast toast = Toast.makeText(RegisterActivity.this, "가입이 완료되었습니다. 로그인을 해주세요.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        finish();
    }
}
