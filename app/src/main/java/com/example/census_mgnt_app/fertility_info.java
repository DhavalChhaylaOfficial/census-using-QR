package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class fertility_info extends AppCompatActivity {

    EditText tbET, tgET, cebET, aabET;
    String tb, tg, ceb, aab;
    Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertility_info);

        tbET = findViewById(R.id.tbET);
        tgET = findViewById(R.id.tgET);
        cebET = findViewById(R.id.cebET);
        aabET = findViewById(R.id.aabET);

        String enu_id = getIntent().getStringExtra("enu_id");
        String uid_4 = getIntent().getStringExtra("uid_4");
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

        String cf = getIntent().getStringExtra("cf");
        String nph = getIntent().getStringExtra("nph");
        String nmc = getIntent().getStringExtra("nmc");
        String nm = getIntent().getStringExtra("nm");
        String nf = getIntent().getStringExtra("nf");
        String nc = getIntent().getStringExtra("nc");
        String nep = getIntent().getStringExtra("nep");
        String enu_name = getIntent().getStringExtra("enu_name");

        nxt = findViewById(R.id.btnnxt);

        nxt.setOnClickListener(v -> {
            tb = tbET.getText().toString();
            tg = tgET.getText().toString();
            ceb = cebET.getText().toString();
            aab = aabET.getText().toString();

            Intent i = new Intent(this, migration_info.class);
            i.putExtra("enu_id", enu_id);
            i.putExtra("uid_5", uid_4);
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

            i.putExtra("tb", tb);
            i.putExtra("tg", tg);
            i.putExtra("ceb", ceb);
            i.putExtra("aab", aab);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });
    }
}