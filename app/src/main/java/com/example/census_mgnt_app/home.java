package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class home extends AppCompatActivity {

    ImageView insert;
    TextView d1,d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        insert = findViewById(R.id.imageView6);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);

        String enu_id = getIntent().getStringExtra("enu_id");
        String enu_name = getIntent().getStringExtra("enu_name");


        d1.setText("NAME : "+enu_name);
        d2.setText("ENUMERATOR ID: "+enu_id);

        insert.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("enu_id",enu_id);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });
    }
}