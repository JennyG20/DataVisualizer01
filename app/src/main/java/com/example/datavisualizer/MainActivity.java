package com.example.datavisualizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button StackBtn, QueueBtn, ListBtn;
    private MainActivity thisMainActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StackBtn = findViewById(R.id.StackBtn);
        QueueBtn = findViewById(R.id.QueueBtn);
        ListBtn = findViewById(R.id.ListBtn);

        StackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisMainActivity, StackActivity.class);
                startActivity(intent);
        QueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(thisMainActivity, QueueActivity.class);
                startActivity(intent);
         ListBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(thisMainActivity, ListActivity.class);
                 startActivity(intent);
             }
         });
            }
        });
            }
        });
    }
}
