package com.example.datavisualizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The MainActivity functions as the landing screen and main menu for the user.
 * The class extends AppCompatActivity which is a base class for custom activities. It contains the
 * controllers for the three buttons on the screen that will take the user to the individual visualizer
 * screens.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * We create the button objects of the MainActivity.
     */
    private Button StackBtn, QueueBtn, ListBtn;
    private MainActivity thisMainActivity = this;

    /**
     * Built in function, it runs whenever you create an instance of an activity .
     *  In this function we initiate the java objects for the buttons and set the onclicklisteners
     *  and say what they are going to do.
     */
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
                /**
                 * This intent object is an abstract description of how we would like to change
                 * an activity to another acitvity.
                 * This intent object will take us to StackActivity.
                 */
                Intent intent = new Intent(thisMainActivity, StackActivity.class);
                startActivity(intent);
            }
        });
        QueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                /**
                 * This intent object is an abstract description of how we would like to change
                 * an activity to another activity.
                 * This intent object will take us to the QueueActivity
                 */
                Intent intent = new Intent(thisMainActivity, QueueActivity.class);
                startActivity(intent);
            }
        });
        ListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * This intent object is an abstract description of how we would like to change
                 * an activity to another activity.
                 * This intent object will take us to the ListActivity.
                 */
                Intent intent = new Intent(thisMainActivity, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
