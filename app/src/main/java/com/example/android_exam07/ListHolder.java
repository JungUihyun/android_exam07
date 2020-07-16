package com.example.android_exam07;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    TextView title;
    TextView content;
    TextView writer;

    public ListHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        title = (TextView)itemView.findViewById(R.id.title);
        content = (TextView)itemView.findViewById(R.id.content);
        writer = (TextView)itemView.findViewById(R.id.writer);

    }
}
