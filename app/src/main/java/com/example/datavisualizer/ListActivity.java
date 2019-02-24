package com.example.datavisualizer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * This activity is the controller class for the list visualizer screen. It contains
 * the list data model and also the controller for the text inputs, the buttons and the Listview
 * displayed on the screen.
 */
public class ListActivity extends AppCompatActivity {

    List<String> ListOfItems;
    ArrayAdapter<String> adapter;

    EditText textInput;
    EditText indexInput;
    private Button addBtn;
    private Button remBtn;

    /**
     * Built in function, it runs whenever you create an instance of an activity .
     * In this function we initiate the java objects for the buttons and set the onclicklisteners
     * and say what they are going to do.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListOfItems = new LinkedList<>();

        ListView lv = findViewById(R.id.ListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListOfItems);

        textInput = findViewById(R.id.editText2);
        indexInput = findViewById(R.id.editText);
        addBtn = findViewById(R.id.addBtn);
        remBtn = findViewById(R.id.remBtn);

        lv.setAdapter(adapter);
        /**
         * The addBtn is set to call the addItem function.
         */
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        /**
         * The removeBtn is going to call the remove function if ListOfItems is not empty.
         */
        remBtn.setOnClickListener(new View.OnClickListener() {
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
     * The removeItem function checks if the user entered an index number and if there is no index number
     * in the input field, it is going to display an error message. Otherwise it removes the correct item
     * and calls the update functions.
     */
    private void removeItem() {
        try {
            if (indexInput.getText().toString().equals("") || Integer.valueOf(indexInput.getText().toString()) < 0 || Integer.valueOf(indexInput.getText().toString()) >= ListOfItems.size()) {

                Context context = getApplicationContext();
                CharSequence text = "Please set a valid index number";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                return;
            }
        } catch (NumberFormatException e) {

            Context context = getApplicationContext();
            CharSequence text = "Please set a valid index number";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

        ListOfItems.remove((int) Integer.valueOf(indexInput.getText().toString().trim()));
        updateIndices();
        adapter.notifyDataSetChanged();
    }

    /**
     *
     */
    private void addItem() {
        try {
            String S = String.valueOf(textInput.getText());
            if (indexInput.getText().toString().equals("")) {
                S = "( " + ListOfItems.size() + " ) " + S;
                ListOfItems.add(S);
            } else {
                ListOfItems.add(Integer.valueOf(indexInput.getText().toString()), S);
            }

            updateIndices();
            adapter.notifyDataSetChanged();
        } catch (NumberFormatException e) {

            Context context = getApplicationContext();
            CharSequence text = "Please set a valid index number";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

    /**
     *
     */
    private void updateIndices() {
        for (int i = 0; i < ListOfItems.size(); i++) {
            ListOfItems.set(i, ListOfItems.get(i).substring(0, 2) + i + ListOfItems.get(i).substring(ListOfItems.get(i).indexOf(" )")));
        }
    }
}
