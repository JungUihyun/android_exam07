package com.example.android_exam07;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ListDatabaseHelper extends SQLiteOpenHelper {

    public static final String tableName = "list_tb";
    public static final int DATABASE_VERSION = 1;

//    public ListDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public ListDatabaseHelper(@Nullable Context context) {
        super(context, tableName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","list db 생성_db가 없을때만 최초로 실행함 (리스트 DB)");
        createTable(db);
    }

    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE " + tableName + "(idx integer primary key autoincrement, title text, content text, user_idx integer)";
        // 임시 데이터
        String dummy1 = "insert into post_tb (title, content, writer) values ('1번째 메모', '첫번째 메모입니다.', '관리자1')";
        String dummy2 = "insert into post_tb (title, content, writer) values ('2번째 메모', '두번째 메모입니다.', '관리자2')";
        String dummy3 = "insert into post_tb (title, content, writer) values ('3번째 메모', '세번째 메모입니다.', '관리자3')";
        try { db.execSQL(dummy1); } catch (SQLException e){ e.printStackTrace(); }
        try { db.execSQL(dummy2); } catch (SQLException e){ e.printStackTrace(); }
        try { db.execSQL(dummy3); } catch (SQLException e){ e.printStackTrace(); }
        try { db.execSQL(sql); } catch (SQLException e){ e.printStackTrace(); }
    }

    public void insertItem(SQLiteDatabase db, String title, String content, int user_idx) {
        Log.i("tag", "글 쓰기를 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName + "(title, content, user_idx)" + "values('" + title + "', '" + content + "', '" + user_idx + "')";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table list_tb");
            onCreate(db);
        }
    }
}
