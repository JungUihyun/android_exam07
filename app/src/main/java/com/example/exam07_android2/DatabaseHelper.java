package com.example.exam07_android2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "list_db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("msg", "DatabseHelper - onCreate()");
        String sql = "CREATE TABLE list_db (idx integer primary key autoincrement, title text, content text, user_idx integer)";
        db.execSQL(sql);

        db.execSQL("insert into list_db (title, content) values ('1번째 메모', '첫번째 메모입니다.')");
        db.execSQL("insert into list_db (title, content) values ('2번째 메모', '두번째 메모입니다.')");
        db.execSQL("insert into list_db (title, content) values ('3번째 메모', '세번째 메모입니다.')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("msg", "DatabseHelper - onUpgrade()");
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table list_db");
            onCreate(db);
        }
    }
}
