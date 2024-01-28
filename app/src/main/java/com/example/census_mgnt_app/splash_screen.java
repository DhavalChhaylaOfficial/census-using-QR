package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.scwang.wave.MultiWaveHeader;


public class splash_screen extends AppCompatActivity {
    Button Start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SwipeButton swipeButton = (SwipeButton)findViewById(R.id.swp_btn);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        Start = findViewById(R.id.start);

        Start.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Login.class));
        });


    }
}