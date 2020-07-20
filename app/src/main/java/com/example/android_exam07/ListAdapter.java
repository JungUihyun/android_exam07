package com.example.android_exam07;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {
    Context context;
    int resId;
    ArrayList<ListVO> datas;
    int cardPosition = 0;

    public ListAdapter(Context context, int resId, ArrayList<ListVO> datas) {
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId, parent, false);
        final ListHolder holder = new ListHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardPosition = holder.getBindingAdapterPosition();
                if(cardPosition != RecyclerView.NO_POSITION) {
                    int itemId = datas.get(cardPosition).getIdx();
                    Intent intent = new Intent(context, ContentActivity.class);
                    intent.putExtra("id", itemId);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "cardview.click : 존재하지 않는 아이템입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cardPosition = holder.getAdapterPosition();
                if(cardPosition != RecyclerView.NO_POSITION) {
                    // DB 삭제
                    int itemId = datas.get(cardPosition).getIdx();

                    ListDatabaseHelper helper = new ListDatabaseHelper(context);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    db.execSQL("delete from users_tb where idx=?", new String[]{Integer.toString(itemId)});
                    db.close();

                    // 화면 갱신
                    datas.remove(cardPosition);
                    notifyItemRemoved(cardPosition);
                    notifyItemRangeChanged(cardPosition, datas.size());
                    Toast.makeText(context, "아이템 삭제됨", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "cardview.cclick : 존재하지 않는 아이템", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView writer = holder.writer;
        TextView content = holder.content;

        ListVO vo = datas.get(position);
        writer.setText(vo.getWriter());
        content.setText(vo.getContent());

        setAnimation(holder.cardView, position);
    }

    private void setAnimation(CardView cardView, int position) {
        Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        cardView.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
