package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the start button
        ImageButton startBtn = findViewById(R.id.startBtn);

        // Set OnClickListener for the start button
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ActivityRvProducts when the button is clicked
                Intent intent = new Intent(MainActivity.this, ActivityRvProducts.class);
                startActivity(intent);
            }
        });
    }
}
