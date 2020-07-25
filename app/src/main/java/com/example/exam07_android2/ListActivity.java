package com.example.exam07_android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    Button btnAdd;
    ArrayList<ListVO> datas;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("메모 목록");

        recyclerView = findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        datas = new ArrayList<>();
        getAllItems();
//
        listAdapter = new ListAdapter(this, R.layout.custom_item, datas);
        recyclerView.setAdapter(listAdapter);
    }

    private void getAllItems() {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.rawQuery("select idx, title, content from list_db order by idx", null);

        while(rs.moveToNext()) {
            ListVO vo = new ListVO();
            vo.setIdx(rs.getInt(0));
            vo.setTitle(rs.getString(1));
            vo.setContent(rs.getString(2));
//            vo.setWriter(rs.getString(3));

            datas.add(vo);
        }
        db.close();
    }

    @Override
    public void onClick(View v) {
        if(v == btnAdd) {
            Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
            intent.putExtra("idx", 0);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 화면 갱신
        datas.clear();
        getAllItems();
        listAdapter.notifyDataSetChanged();
    }
}