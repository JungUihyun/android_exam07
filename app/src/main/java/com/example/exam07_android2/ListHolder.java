package com.example.exam07_android2;

import android.view.View;
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

        cardView = itemView.findViewById(R.id.cardview);
        title = (TextView)itemView.findViewById(R.id.item_title);
        content = (TextView)itemView.findViewById(R.id.item_content);
        writer = (TextView)itemView.findViewById(R.id.item_writer);
    }
}
