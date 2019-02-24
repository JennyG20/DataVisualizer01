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

/**
 * This activity is the controller class for the stack visualizer screen. It contains
 * the stack data model and also the controller for the text input, the buttons and the listview
 * displayed on the screen.
 */
public class StackActivity extends AppCompatActivity {

    private Stack<String> ListOfItems;
    private ArrayAdapter<String> adapter;

    private EditText input;
    private Button PsBtn;
    private Button PopBtn;

    /**
     * Built in function, it runs whenever you create an instance of an activity .
     * In this function we initiate the java objects for the buttons and set the onclicklisteners
     * and say what they are going to do. We also initiate our stack and our adapter that we are
     * going to use to connect our data model to the ListView
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        ListOfItems = new Stack<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListOfItems);

        input = findViewById(R.id.editText2);
        PsBtn = findViewById(R.id.EnBtn);
        PopBtn = findViewById(R.id.PopBtn);

        ListView lv = findViewById(R.id.ListView);
        lv.setAdapter(adapter);
        /**
         * The PsBtn is set to trigger the addItem function
         */
        PsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        /**
         * The PopBtn is set to call the removeItem function if the ListOfItems is not empty.
         */
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

    /**
     * The removeItem function first reverses the stack because theListView is displayed in a reversed
     * way, so it appears correctly. After it we call pop on the stack removing the item from the top
     * then the stack is reversed back to its original state and the adapter is asked to update the
     * content of the ListView according to ListOfItems.
     */
    private void removeItem() {
        Collections.reverse(ListOfItems);
        ListOfItems.pop();
        Collections.reverse(ListOfItems);
        adapter.notifyDataSetChanged();
    }

    /**
     * First we read the content of the input text field into a local string then the index number
     * of the new value is added at the beginning of the string, then we push the string to the stack
     * and update the ListView by notifying the adapter.
     */
    private void addItem() {
        String S = String.valueOf(input.getText());
        S = "( " + ListOfItems.size() + " ) " + S;
        Collections.reverse(ListOfItems);
        ListOfItems.push(S);
        Collections.reverse(ListOfItems);
        adapter.notifyDataSetChanged();
    }
}

