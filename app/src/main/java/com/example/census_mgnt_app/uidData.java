package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class uidData extends AppCompatActivity {
    TextView uidtxt, nametxt, mobtxt, addresstxt, gendertxt, casttxt, dobtxt;

    Button fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uid_data);

        uidtxt = findViewById(R.id.uidtxt);
        nametxt = findViewById(R.id.nametxt);
        mobtxt = findViewById(R.id.mobtxt);
        addresstxt = findViewById(R.id.addresstxt);
        gendertxt = findViewById(R.id.gendertxt);
        casttxt = findViewById(R.id.casttxt);
        dobtxt = findViewById(R.id.dobtxt);
        fetch = findViewById(R.id.Fetch);

        String uid = getIntent().getStringExtra("uid");
        String enu_name = getIntent().getStringExtra("enu_name");

        uidtxt.setText(uid);
        nametxt.setText(getIntent().getStringExtra("name"));
        addresstxt.setText(getIntent().getStringExtra("address"));
        mobtxt.setText( getIntent().getStringExtra("mob"));
        gendertxt.setText(getIntent().getStringExtra("gender"));
        casttxt.setText(getIntent().getStringExtra("cast"));
        dobtxt.setText(getIntent().getStringExtra("dob"));

        String enu_id = getIntent().getStringExtra("enu_id");

        fetch.setOnClickListener(v -> {
            Intent i = new Intent(this, head_info.class);
            i.putExtra("uid_1", uid);
            i.putExtra("enu_id", enu_id);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });
    }

}