package com.example.android_exam07;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ListDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String tableName = "list_tb";

    public ListDatabaseHelper(@Nullable Context context) {
        super(context, tableName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE list_tb (idx integer primary key autoincrement, title text, content text, user_idx integer)";
        // 임시 데이터
        String dummy1 = "insert into list_tb (title, content, writer) values ('1번째 메모', '첫번째 메모입니다.', '관리자1')";
        String dummy2 = "insert into list_tb (title, content, writer) values ('2번째 메모', '두번째 메모입니다.', '관리자2')";
        String dummy3 = "insert into list_tb (title, content, writer) values ('3번째 메모', '세번째 메모입니다.', '관리자3')";

        db.execSQL(dummy1);
        db.execSQL(dummy2);
        db.execSQL(dummy3);
        db.execSQL(sql);
    }

    public void insertItem(SQLiteDatabase db, String title, String content, int user_idx) {
        Log.i("tag", "글 쓰기를 했을때 실행함");
        db.beginTransaction();

        String sql = "INSERT INTO list_tb (title, content)" + "values('" + title + "', '" + content + "')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table list_tb");
            onCreate(db);
        }
    }
}
