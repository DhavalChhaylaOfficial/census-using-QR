package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class family_info extends AppCompatActivity {
    EditText tnpET, mcET, tmET, tfET, tcET, epET;
    RadioButton good, moderate, bad;
    Button nxt;
    String nph, nmc, nm, nf, nc, nep, cf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);

        tnpET = findViewById(R.id.tnpET);
        mcET = findViewById(R.id.mcET);
        tmET = findViewById(R.id.tmET);
        tfET = findViewById(R.id.tfET);
        tcET = findViewById(R.id.tcET);
        epET = findViewById(R.id.epET);

        good = findViewById(R.id.Good);
        moderate = findViewById(R.id.Moderate);
        bad = findViewById(R.id.Bad);

        String enu_id = getIntent().getStringExtra("enu_id");
        String uid_3 = getIntent().getStringExtra("uid_3");
        String education = getIntent().getStringExtra("education");
        String fos = getIntent().getStringExtra("fos");
        String mt = getIntent().getStringExtra("mt");
        String foo = getIntent().getStringExtra("foo");
        String hc = getIntent().getStringExtra("hc");
        String ds = getIntent().getStringExtra("ds");
        String dtw = getIntent().getStringExtra("dtw");
        String mot = getIntent().getStringExtra("mot");

        String pmh = getIntent().getStringExtra("pmh");
        String ho = getIntent().getStringExtra("ho");
        String uoh = getIntent().getStringExtra("uoh");
        String coh = getIntent().getStringExtra("coh");
        String msw = getIntent().getStringExtra("msw");
        String msl = getIntent().getStringExtra("msl");
        String msf = getIntent().getStringExtra("msf");
        String as = getIntent().getStringExtra("as");
        String lsf = getIntent().getStringExtra("lsf");
        String dnh = getIntent().getStringExtra("dnh");
        String lsu = getIntent().getStringExtra("lsu");
        String lor = getIntent().getStringExtra("lor");
        String enu_name = getIntent().getStringExtra("enu_name");

        nxt = findViewById(R.id.btnnxt);


        nxt.setOnClickListener(v -> {
            nph = tnpET.getText().toString();
            nmc = mcET.getText().toString();
            nm = tmET.getText().toString();
            nf = tfET.getText().toString();
            nc = tcET.getText().toString();
            nep = epET.getText().toString();

            if (good.isChecked()) {
                cf = good.getText().toString();
            }
            if (moderate.isChecked()) {
                cf = moderate.getText().toString();
            }
            if (bad.isChecked()) {
                cf = bad.getText().toString();
            }

            Intent i = new Intent(this, fertility_info.class);
            i.putExtra("enu_id", enu_id);
            i.putExtra("uid_4", uid_3);
            i.putExtra("education", education);
            i.putExtra("fos", fos);
            i.putExtra("mt", mt);
            i.putExtra("foo", foo);
            i.putExtra("hc", hc);
            i.putExtra("ds", ds);
            i.putExtra("dtw", dtw);
            i.putExtra("mot", mot);

            i.putExtra("pmh", pmh);
            i.putExtra("ho", ho);
            i.putExtra("uoh", uoh);
            i.putExtra("coh", coh);
            i.putExtra("msw", msw);
            i.putExtra("msl", msl);
            i.putExtra("msf", msf);
            i.putExtra("as", as);
            i.putExtra("lsf", lsf);
            i.putExtra("dnh", dnh);
            i.putExtra("lsu", lsu);
            i.putExtra("lor", lor);

            i.putExtra("cf", cf);
            i.putExtra("nph", nph);
            i.putExtra("nmc", nmc);
            i.putExtra("nm", nm);
            i.putExtra("nf", nf);
            i.putExtra("nc", nc);
            i.putExtra("nep", nep);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });
    }
}