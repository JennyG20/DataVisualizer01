package com.example.datavisualizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class StackActivity extends AppCompatActivity {

    public ArrayList<String> ListOfItems;
    ArrayAdapter<String> adapter;
    EditText input;
    Button PsBtn;
    private StackActivity thisStackActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        ListOfItems = new ArrayList<>();
        ListView lv = findViewById(R.id.ListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListOfItems);
        input = findViewById(R.id.editText2);
        PsBtn = findViewById(R.id.PsBtn);
        lv.setAdapter(adapter);

        PsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }
    public void addItem() {
        ListOfItems.add(input.getText().toString());
        adapter.notifyDataSetChanged();

    }
}

