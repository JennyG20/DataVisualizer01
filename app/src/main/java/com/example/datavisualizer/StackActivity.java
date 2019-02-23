package com.example.datavisualizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Collections;
import java.util.Stack;

public class StackActivity extends AppCompatActivity {

    private Stack<String> ListOfItems;
    private ArrayAdapter<String> adapter;

    private EditText input;
    private Button PsBtn;
    private Button PopBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        ListOfItems = new Stack<>();

        ListView lv = findViewById(R.id.ListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListOfItems);

        input = findViewById(R.id.editText2);
        PsBtn = findViewById(R.id.EnBtn);
        PopBtn = findViewById(R.id.PopBtn);

        lv.setAdapter(adapter);

        PsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        PopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ListOfItems.size() == 0) {
                } else {
                    removeItem();
                }
            }
        });
    }

    private void removeItem() {
        Collections.reverse(ListOfItems);
        ListOfItems.pop();
        Collections.reverse(ListOfItems);
        adapter.notifyDataSetChanged();
    }

    private void addItem() {
        String S = String.valueOf(input.getText());
        S = "( " + ListOfItems.size() + " ) " + S;
        Collections.reverse(ListOfItems);
        ListOfItems.push(S);
        Collections.reverse(ListOfItems);
        adapter.notifyDataSetChanged();
    }
}

