package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class head_info extends AppCompatActivity {

    Spinner spinner, spinner1, spinner2;

    Button nxt;
    RadioButton below10, above10, gradaute, eng, med, bus, art, oth, Intellectual, Physical, Sensory, mentalillness, non, NT, NT1, NT2, NT3, NT4, walking, Cycling, Public, Private, Rail, None;
    String education, fos, mt, foo, ds, hc, dtw, mot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_info);

        String enu_id = getIntent().getStringExtra("enu_id");
        String uid_1 = getIntent().getStringExtra("uid_1");
        String enu_name = getIntent().getStringExtra("enu_name");

        below10 = findViewById(R.id.below10);
        above10 = findViewById(R.id.above10);
        gradaute = findViewById(R.id.gradaute);

        eng = findViewById(R.id.eng);
        med = findViewById(R.id.med);
        bus = findViewById(R.id.bus);
        art = findViewById(R.id.art);
        oth = findViewById(R.id.oth);

        Intellectual = findViewById(R.id.Intellectual);
        Physical = findViewById(R.id.Physical);
        Sensory = findViewById(R.id.Sensory);
        mentalillness = findViewById(R.id.mentalillness);
        non = findViewById(R.id.non);

        NT = findViewById(R.id.NT);
        NT1 = findViewById(R.id.NT1);
        NT2 = findViewById(R.id.NT2);
        NT3 = findViewById(R.id.NT3);
        NT4 = findViewById(R.id.NT4);

        walking = findViewById(R.id.walking);
        Cycling = findViewById(R.id.Cycling);
        Public = findViewById(R.id.Public);
        Private = findViewById(R.id.Private);
        Rail = findViewById(R.id.Rail);
        None = findViewById(R.id.None);

        nxt = findViewById(R.id.btn_nxt);

        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        String[] m_tong = {"SELECT", "Bengali", "Bodo", "Dogri", "Gujarati", "Hindi", "Kannada", "Kashmiri", "Dogri", "Konkani", "Maithili", "Assamese", "Bodo", "Malayalam", "Manipuri", "Marathi", "Nepali", "Odia", "Punjabi", "Sanskrit", "Santali", "Sindhi", "Tamil", "Telugu", "Urdu"};
        String[] occupation = {"SELECT", "Architecture and engineering", " Arts, culture, and entertainment", "Business, management, and administration", "Community and social services", "Education", "Science and technology", "Installation, repair and maintenance", "Farming, fishing, and forestry", "Government", "Health and medicine", " Law and public policy", "None"};
        String[] h_cond = {"SELECT", " Cardiovascular diseases", " Respiratory diseases", "Digestive diseases", "Neurological diseases", "Endocrine diseases", "Musculoskeletal diseases", " Renal diseases", "Reproductive and sexual health conditions", "Skin diseases", "None"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                head_info.this,
                android.R.layout.simple_spinner_item,
                m_tong
        );
        spinner.setAdapter(adapter);
        spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                head_info.this,
                android.R.layout.simple_spinner_item,
                occupation
        );
        spinner1.setAdapter(adapter1);
        spinner1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                head_info.this,
                android.R.layout.simple_spinner_item,
                h_cond
        );
        spinner2.setAdapter(adapter2);
        spinner2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        nxt.setOnClickListener(v -> {
            if (below10.isChecked()) {
                education = below10.getText().toString();
            }
            if (above10.isChecked()) {
                education = above10.getText().toString();
            }
            if (gradaute.isChecked()) {
                education = gradaute.getText().toString();
            }

            if (eng.isChecked()) {
                fos = eng.getText().toString();
            }
            if (med.isChecked()) {
                fos = med.getText().toString();
            }
            if (bus.isChecked()) {
                fos = bus.getText().toString();
            }
            if (art.isChecked()) {
                fos = art.getText().toString();
            }
            if (oth.isChecked()) {
                fos = oth.getText().toString();
            }

            if (Intellectual.isChecked()) {
                ds = Intellectual.getText().toString();
            }
            if (Physical.isChecked()) {
                ds = Physical.getText().toString();
            }
            if (Sensory.isChecked()) {
                ds = Sensory.getText().toString();
            }
            if (mentalillness.isChecked()) {
                ds = mentalillness.getText().toString();
            }
            if (non.isChecked()) {
                ds = non.getText().toString();
            }

            if (NT.isChecked()) {
                dtw = NT.getText().toString();
            }
            if (NT1.isChecked()) {
                dtw = NT1.getText().toString();
            }
            if (NT2.isChecked()) {
                dtw = NT2.getText().toString();
            }
            if (NT3.isChecked()) {
                dtw = NT3.getText().toString();
            }
            if (NT4.isChecked()) {
                dtw = NT4.getText().toString();
            }

            if (walking.isChecked()) {
                mot = walking.getText().toString();
            }
            if (Cycling.isChecked()) {
                mot = Cycling.getText().toString();
            }
            if (Public.isChecked()) {
                mot = Public.getText().toString();
            }
            if (Private.isChecked()) {
                mot = Private.getText().toString();
            }
            if (Rail.isChecked()) {
                mot = Rail.getText().toString();
            }
            if (None.isChecked()) {
                mot = None.getText().toString();
            }

            mt = spinner.getSelectedItem().toString();
            foo = spinner1.getSelectedItem().toString();
            hc = spinner2.getSelectedItem().toString();

            Intent i = new Intent(head_info.this, home_info.class);
            i.putExtra("enu_id", enu_id);
            i.putExtra("uid_2", uid_1);
            i.putExtra("education", education);
            i.putExtra("fos", fos);
            i.putExtra("mt", mt);
            i.putExtra("foo", foo);
            i.putExtra("hc", hc);
            i.putExtra("ds", ds);
            i.putExtra("dtw", dtw);
            i.putExtra("mot", mot);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });
    }
}