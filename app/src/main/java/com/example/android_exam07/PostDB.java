package com.example.android_exam07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PostDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public PostDB(@Nullable Context context) {
        super(context, "post_tb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table post_tb (idx integer primary key autoincrement, title text, content text, writer text)";
        db.execSQL("insert into post_tb (title, content, writer) values ('1번째 메모', '첫번째 메모입니다.', '관리자')");
        db.execSQL("insert into post_tb (title, content, writer) values ('2번째 메모', '두번째 메모입니다.', '관리자')");
        db.execSQL("insert into post_tb (title, content, writer) values ('3번째 메모', '세번째 메모입니다.', '관리자')");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table post_tb");
            onCreate(db);
        }
    }
}
