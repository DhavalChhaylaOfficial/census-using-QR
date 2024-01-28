package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class home_info extends AppCompatActivity {

    String pmh, ho, uoh, coh, msw, msl, msf, as, lsf, dnh, lsu, lor;
    RadioButton Wood, Brick, Concrete, Stone, Adobe, Freehold, Leasehold, Condominium, Cooperative, Timeshare, Primaryresidence, Rentalproperty, Vacationhome, Homeoffice, mgl, Good, Moderate, Bad;
    RadioButton sw, gw, mws, bw, nn, Electricity, Solar, Kerosene, AnyOther, NoLight, lpg, Naturalgas, Wood1, Charcoal, Kerosene1, Flushtoilets, Pitlatrines, Publictoilets, Opendefecation, n;
    RadioButton yes, no, f1, f2, f3, f4, l1, l2, l3, l4, fd1, fd2, fd3;
    Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info);

        Wood = findViewById(R.id.Wood);
        Brick = findViewById(R.id.Brick);
        Concrete = findViewById(R.id.Concrete);
        Stone = findViewById(R.id.Stone);
        Adobe = findViewById(R.id.Adobe);

        Freehold = findViewById(R.id.Freehold);
        Leasehold = findViewById(R.id.Leasehold);
        Condominium = findViewById(R.id.Condominium);
        Cooperative = findViewById(R.id.Cooperative);
        Timeshare = findViewById(R.id.Timeshare);

        Primaryresidence = findViewById(R.id.Primaryresidence);
        Rentalproperty = findViewById(R.id.Rentalproperty);
        Vacationhome = findViewById(R.id.Vacationhome);
        Homeoffice = findViewById(R.id.Homeoffice);
        mgl = findViewById(R.id.mgl);

        Good = findViewById(R.id.Good);
        Moderate = findViewById(R.id.Moderate);
        Bad = findViewById(R.id.Bad);

        sw = findViewById(R.id.sw);
        gw = findViewById(R.id.gw);
        mws = findViewById(R.id.mws);
        bw = findViewById(R.id.bw);
        nn = findViewById(R.id.nn);

        Electricity = findViewById(R.id.Electricity);
        Solar = findViewById(R.id.Solar);
        Kerosene = findViewById(R.id.Kerosene);
        AnyOther = findViewById(R.id.AnyOther);
        NoLight = findViewById(R.id.NoLight);

        lpg = findViewById(R.id.lpg);
        Naturalgas = findViewById(R.id.Naturalgas);
        Wood1 = findViewById(R.id.Wood1);
        Charcoal = findViewById(R.id.Charcoal);
        Kerosene1 = findViewById(R.id.Kerosene1);

        Flushtoilets = findViewById(R.id.Flushtoilets);
        Pitlatrines = findViewById(R.id.Pitlatrines);
        Publictoilets = findViewById(R.id.Publictoilets);
        Opendefecation = findViewById(R.id.Opendefecation);
        n = findViewById(R.id.n);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);

        fd1 = findViewById(R.id.fd1);
        fd2 = findViewById(R.id.fd2);
        fd3 = findViewById(R.id.fd3);


        String enu_id = getIntent().getStringExtra("enu_id");
        String uid_2 = getIntent().getStringExtra("uid_2");
        String education = getIntent().getStringExtra("education");
        String fos = getIntent().getStringExtra("fos");
        String mt = getIntent().getStringExtra("mt");
        String foo = getIntent().getStringExtra("foo");
        String hc = getIntent().getStringExtra("hc");
        String ds = getIntent().getStringExtra("ds");
        String dtw = getIntent().getStringExtra("dtw");
        String mot = getIntent().getStringExtra("mot");
        String enu_name = getIntent().getStringExtra("enu_name");

        nxt = findViewById(R.id.btn_nxt);

        nxt.setOnClickListener(v -> {

            if (Wood.isChecked()) {
                pmh = Wood.getText().toString();
            }
            if (Brick.isChecked()) {
                pmh = Brick.getText().toString();
            }
            if (Concrete.isChecked()) {
                pmh = Concrete.getText().toString();
            }
            if (Stone.isChecked()) {
                pmh = Stone.getText().toString();
            }
            if (Adobe.isChecked()) {
                pmh = Adobe.getText().toString();
            }

            if (Freehold.isChecked()) {
                ho = Freehold.getText().toString();
            }
            if (Leasehold.isChecked()) {
                ho = Leasehold.getText().toString();
            }
            if (Condominium.isChecked()) {
                ho = Condominium.getText().toString();
            }
            if (Cooperative.isChecked()) {
                ho = Cooperative.getText().toString();
            }
            if (Timeshare.isChecked()) {
                ho = Timeshare.getText().toString();
            }

            if (Primaryresidence.isChecked()) {
                uoh = Primaryresidence.getText().toString();
            }
            if (Rentalproperty.isChecked()) {
                uoh = Rentalproperty.getText().toString();
            }
            if (Vacationhome.isChecked()) {
                uoh = Vacationhome.getText().toString();
            }
            if (Homeoffice.isChecked()) {
                uoh = Homeoffice.getText().toString();
            }
            if (mgl.isChecked()) {
                uoh = mgl.getText().toString();
            }

            if (Good.isChecked()) {
                coh = Good.getText().toString();
            }
            if (Moderate.isChecked()) {
                coh = Moderate.getText().toString();
            }
            if (Bad.isChecked()) {
                coh = Bad.getText().toString();
            }

            if (sw.isChecked()) {
                msw = sw.getText().toString();
            }
            if (gw.isChecked()) {
                msw = gw.getText().toString();
            }
            if (mws.isChecked()) {
                msw = mws.getText().toString();
            }
            if (bw.isChecked()) {
                msw = bw.getText().toString();
            }
            if (nn.isChecked()) {
                msw = nn.getText().toString();
            }

            if (Electricity.isChecked()) {
                msl = Electricity.getText().toString();
            }
            if (Solar.isChecked()) {
                msl = Solar.getText().toString();
            }
            if (Kerosene.isChecked()) {
                msl = Kerosene.getText().toString();
            }
            if (AnyOther.isChecked()) {
                msl = AnyOther.getText().toString();
            }
            if (NoLight.isChecked()) {
                msl = NoLight.getText().toString();
            }

            if (lpg.isChecked()) {
                msf = lpg.getText().toString();
            }
            if (Naturalgas.isChecked()) {
                msf = Naturalgas.getText().toString();
            }
            if (Wood1.isChecked()) {
                msf = Wood1.getText().toString();
            }
            if (Charcoal.isChecked()) {
                msf = Charcoal.getText().toString();
            }
            if (Kerosene1.isChecked()) {
                msf = Kerosene1.getText().toString();
            }

            if (Flushtoilets.isChecked()) {
                as = Flushtoilets.getText().toString();
            }
            if (Pitlatrines.isChecked()) {
                as = Pitlatrines.getText().toString();
            }
            if (Publictoilets.isChecked()) {
                as = Publictoilets.getText().toString();
            }
            if (Opendefecation.isChecked()) {
                as = Opendefecation.getText().toString();
            }
            if (n.isChecked()) {
                as = n.getText().toString();
            }

            if (yes.isChecked()) {
                lsf = yes.getText().toString();
            }
            if (no.isChecked()) {
                lsf = no.getText().toString();
            }

            if (f1.isChecked()) {
                dnh = f1.getText().toString();
            }
            if (f2.isChecked()) {
                dnh = f2.getText().toString();
            }
            if (f3.isChecked()) {
                dnh = f3.getText().toString();
            }
            if (f4.isChecked()) {
                dnh = f4.getText().toString();
            }

            if (l1.isChecked()) {
                lsu = l1.getText().toString();
            }
            if (l2.isChecked()) {
                lsu = l2.getText().toString();
            }
            if (l3.isChecked()) {
                lsu = l3.getText().toString();
            }
            if (l4.isChecked()) {
                lsu = l4.getText().toString();
            }

            if (fd1.isChecked()) {
                lor = fd1.getText().toString();
            }
            if (fd2.isChecked()) {
                lor = fd2.getText().toString();
            }
            if (fd3.isChecked()) {
                lor = fd3.getText().toString();
            }

            Intent i = new Intent(this, family_info.class);
            i.putExtra("enu_id", enu_id);
            i.putExtra("uid_3", uid_2);
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
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });
    }
}