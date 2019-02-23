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


public class ListActivity extends AppCompatActivity {

    List<String> ListOfItems;
    ArrayAdapter<String> adapter;

    EditText textInput;
    EditText indexInput;
    private Button addBtn;
    private Button remBtn;


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

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
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

    private void removeItem() {
        if(indexInput.getText().toString().equals("") || Integer.valueOf(indexInput.getText().toString()) < 0 || Integer.valueOf(indexInput.getText().toString()) >= ListOfItems.size()){

            Context context = getApplicationContext();
            CharSequence text = "Please set a valid index number";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }

        ListOfItems.remove((int) Integer.valueOf(indexInput.getText().toString().trim()));
        updateIndices();
        adapter.notifyDataSetChanged();
    }

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
        }catch (NumberFormatException e){

            Context context = getApplicationContext();
            CharSequence text = "Please set a valid index number";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

    private void updateIndices() {
        for (int i = 0; i < ListOfItems.size(); i++) {
            ListOfItems.set(i, ListOfItems.get(i).substring(0, 2) + i + ListOfItems.get(i).substring(ListOfItems.get(i).indexOf(" )")));
        }
    }
}
