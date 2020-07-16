package com.example.android_exam07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ListVO> datas;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("글 목록");

        recyclerView = findViewById(R.id.recyclerview);

        datas = new ArrayList<>();
        getAllDatas();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListAdapter(this, R.layout.custom_item, datas);
        recyclerView.setAdapter(adapter);
    }

    private void getAllDatas() {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.rawQuery("select idx, title, content, writer from post_tb order by idx", null);

        while(rs.moveToNext()) {
            ListVO vo = new ListVO();
            vo.setIdx(rs.getInt(0));
            vo.setTitle(rs.getString(1));
            vo.setContent(rs.getString(2));
            vo.setWriter(rs.getString(3));
            datas.add(vo);
        }
        db.close();
    }
}