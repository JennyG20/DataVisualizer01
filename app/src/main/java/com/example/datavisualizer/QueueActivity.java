package com.example.datavisualizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class QueueActivity extends AppCompatActivity {

    Queue<String> ListOfItems;
    ArrayAdapter<String> adapter;

    EditText input;
    private Button EnBtn;
    private Button DeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        ListOfItems = new LinkedList<>();

        ListView lv = findViewById(R.id.ListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, (List) ListOfItems);

        input = findViewById(R.id.editText2);
        EnBtn = findViewById(R.id.EnBtn);
        DeBtn = findViewById(R.id.DeBtn);

        lv.setAdapter(adapter);

        EnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        DeBtn.setOnClickListener(new View.OnClickListener() {
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
        ListOfItems.poll();
        updateIndices();
        updateNext();
        adapter.notifyDataSetChanged();
    }

    private void addItem() {
        String S = String.valueOf(input.getText());
        S = "( " + ListOfItems.size() + " ) " + S;
        ListOfItems.offer(S);
        updateIndices();
        updateNext();
        adapter.notifyDataSetChanged();
    }

    //for loop go through the list of items (all) and replaces the 3rd char which is the actual number in the string
    // to i which is the current index .
    private void updateIndices() {
        for (int i = 0; i < ListOfItems.size(); i++) {
            String s = ListOfItems.poll();
            ListOfItems.offer(s.substring(0, 2) + i + s.substring(2 + Integer.valueOf(String.valueOf(i).length())));
        }
    }

    private void updateNext() {
        for (int i = 0; i < ListOfItems.size(); i++) {
            String s = ListOfItems.poll();
            if (s.contains(" next : ")) s = s.substring(0, s.indexOf(" next : "));
            if (ListOfItems.peek() != null) {
                if (!ListOfItems.peek().contains("( 0 )")) {
                    s += " next : " + (ListOfItems.peek().contains(" next : ") ? ListOfItems.peek().substring(0, ListOfItems.peek().indexOf(" next : ")) : ListOfItems.peek());
                } else {
                    s += " next : null";
                }
            } else {
                s += " next : null";
            }
            ListOfItems.offer(s);
        }
    }
}
