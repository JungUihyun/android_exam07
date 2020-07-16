package com.example.android_exam07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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
    private int cardPos = 0;

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
                cardPos = holder.getBindingAdapterPosition();
                if(cardPos != RecyclerView.NO_POSITION) {
                    String strName = datas.get(cardPos).getWriter();
                    Toast.makeText(context, strName + " : 글쓴이 이름", Toast.LENGTH_SHORT).show();
                }
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
