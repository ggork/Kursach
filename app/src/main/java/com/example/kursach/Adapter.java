package com.example.kursach;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<String> Message;

    LayoutInflater inflater;

    public  Adapter(Context context, ArrayList<String> message) {
        Message = message;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    //отвечает за отображение элементов
    public void onBindViewHolder(@NonNull ViewHolder Holder, int position) {
        String msg = Message.get(position);
        Holder.messages.setText(msg);
    }

    @Override
    //отображет сколько сообщений у нас есть
    public int getItemCount() {
        return Message.size();
    }
}
