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

/**
 * This activity is the controller class for the queue visualizer screen. It contains
 * the queue data model and also the controller for the text input, the buttons and the listview
 * displayed on the screen.
 */
public class QueueActivity extends AppCompatActivity {

    Queue<String> ListOfItems;
    ArrayAdapter<String> adapter;

    private EditText input;
    private Button EnBtn;
    private Button DeBtn;

    /**
     * Built in function, it runs whenever you create an instance of an activity .
     * In this function we initiate the java objects for the buttons and set the onclicklisteners
     * and say what they are going to do. We also initiate our queue and our adapter that we are
     * going to use to connect our data model to the ListView
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        ListOfItems = new LinkedList<>();

        ListView lv = findViewById(R.id.ListView);
        /**
         * This is where we initiate our adapter and we have to cast our queue into a list because
         * the implementation of queue that we are using can only be used with a linked list, but the
         * ArrayAdapter requires a List.
         */
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, (List) ListOfItems);

        input = findViewById(R.id.editText2);
        EnBtn = findViewById(R.id.EnBtn);
        DeBtn = findViewById(R.id.DeBtn);

        lv.setAdapter(adapter);
        /**
         * The EnBtn is set to call the addItem function.
         */
        EnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        /**
         * The DeBtn is set to remove an item is ListOfItems is empty.
         */
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

    /**
     * This function removes an item from the queue and calls the updater functions.
     */
    private void removeItem() {
        ListOfItems.poll();
        updateIndices();
        updateNext();
        adapter.notifyDataSetChanged();
    }

    /**
     * First we read the content of the input text field into a local string then the index number
     * of the new value is added at the beginning of the string, then we enqueue the string and call
     * the updater functions.
     */
    private void addItem() {
        String S = String.valueOf(input.getText());
        S = "( " + ListOfItems.size() + " ) " + S;
        ListOfItems.offer(S);
        updateIndices();
        updateNext();
        adapter.notifyDataSetChanged();
    }

    /**
     * This function is responsible for updating the index numbers of the list items after adding or
     * removing an item.
     */
    private void updateIndices() {
        for (int i = 0; i < ListOfItems.size(); i++) {
            String s = ListOfItems.poll();
            ListOfItems.offer(s.substring(0, 2) + i + s.substring(2 + Integer.valueOf(String.valueOf(i).length())));
        }
    }

    /**
     *  This function will update the next item references for every item in the list.
     */
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
