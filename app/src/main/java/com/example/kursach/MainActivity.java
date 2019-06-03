package com.example.kursach;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   //подлючение к бд
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    Button Button_send;
    EditText Message_text;
    //элемент хранящий сообщения
    ArrayList<String> Messages = new ArrayList<>();
    RecyclerView mesRecylcle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //поиск id объектов
        Button_send = findViewById(R.id.but_in);
        Message_text = findViewById(R.id.message_in);
        mesRecylcle = findViewById(R.id.rec_id);
        //создание адаптера
        final Adapter adapter = new Adapter(this, Messages);

        //конструктор ленты сообщений
        mesRecylcle.setLayoutManager(new LinearLayoutManager(this));
        mesRecylcle.setAdapter(adapter);


        //обработчик нажатия кнопки
        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //получение строки сообщения
                String mes = Message_text.getText().toString();
                //проверки на пустое сообщения и на длину сообщения
                if(mes.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Введите сообщение!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mes.length()==255)
                {
                    Toast.makeText(getApplicationContext(),"Слишком длинное сообщение!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //отправка сообщения в базу данных
                myRef.push().setValue(mes);
                Message_text.setText("");
            }
        });
        //обработчик получения сообщения
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String mes = dataSnapshot.getValue(String.class);
                Messages.add(mes);
                //смотрит изменение в бд
                adapter.notifyDataSetChanged();
                //прокрутка к последнему соощению
                mesRecylcle.smoothScrollToPosition(Messages.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}