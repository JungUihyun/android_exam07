package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText titleInput, contentInput;
    Button save;
    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setTitle("상세보기");

        titleInput = (EditText)findViewById(R.id.title);
        contentInput = (EditText)findViewById(R.id.content);
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(this);

        Intent intent = getIntent();
        idx = intent.getIntExtra("idx", 0);

        if(idx > 0) {
            ListDatabaseHelper helper = new ListDatabaseHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor rs = db.rawQuery("select title, content from list_tb where idx=?", new String[]{Integer.toString(idx)});

            while(rs.moveToNext()) {
                titleInput.setText(rs.getString(0));
                contentInput.setText(rs.getString(1));
            }
            db.close();
        }
    }

    @Override
    public void onClick(View v) {
        String title = titleInput.getText().toString();
        String content = contentInput.getText().toString();

        ListDatabaseHelper helper = new ListDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        if(v ==  save) {
            // 화면에 있는 내용을 DB에 저장
            if(idx == 0) {
                // DB에 insert
                db.execSQL("insert into list_tb (title, content) values (?, ?)", new String[]{title, content});
                Toast.makeText(getApplicationContext(), "메모추가함", Toast.LENGTH_SHORT).show();
            } else if (idx > 0) {
                // DB에 update
                db.execSQL("update list_tb set title=?, content=? where idx=?", new String[]{title, content, Integer.toString(idx)});
                Toast.makeText(getApplicationContext(), "메모수정됨", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
        finish();
    }
}