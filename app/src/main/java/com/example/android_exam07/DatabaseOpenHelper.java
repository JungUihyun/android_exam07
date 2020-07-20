package com.example.android_exam07;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String tableName = "users_tb";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag", "db 생성_db가 없을때만 최초로 실행함");
        createTable(db);
    }

    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE " + tableName + "(idx integer primary key autoincrement, id text, password text, name text, post_id integer)";
        try { db.execSQL(sql); } catch (SQLException e){ e.printStackTrace(); }
    }

    public void insertUser(SQLiteDatabase db, String id, String password){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName + "(id, password)" + "values('"+ id +"', '"+password+"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table users_tb");
            onCreate(db);
        }
    }
}
