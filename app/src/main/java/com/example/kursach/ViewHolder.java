package com.example.kursach;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    //поле где хранится сообщение
    TextView messages;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        messages = itemView.findViewById(R.id.item_id);
    }
}
