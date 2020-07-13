package com.example.android_exam07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public UserDB(@Nullable Context context) {
        super(context, "user_tb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user_tb (_id integer primary key autoincrement, id text, password text, name text, post_id integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table user_tb");
            onCreate(db);
        }
    }
}
