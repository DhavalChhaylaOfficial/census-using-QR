package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import kotlin.jvm.internal.Lambda;

public class final_sub extends AppCompatActivity {
    TextView h_uid, emp_id;
    CheckBox cb1;
    Button final_sub;
    String head_uid, enu_id, education, fos, mt, foo, hc, ds, dtw, mot, pmh, ho, uoh, coh, msw, msl, msf, as, lsf, dnh, lsu, lor, cf, nph, nmc, nm, nf, nc, nep, tb, tg, ceb, aab, rfm, fw, ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_sub);


        head_uid = getIntent().getStringExtra("uid_6");
        enu_id = getIntent().getStringExtra("enu_id");

        h_uid = findViewById(R.id.h_uid);
        emp_id = findViewById(R.id.emp_id);

        cb1 = findViewById(R.id.cb1);

        final_sub = findViewById(R.id.btn_final);
        final_sub.setVisibility(View.INVISIBLE);

        education = getIntent().getStringExtra("education");
        fos = getIntent().getStringExtra("fos");
        mt = getIntent().getStringExtra("mt");
        foo = getIntent().getStringExtra("foo");
        hc = getIntent().getStringExtra("hc");
        ds = getIntent().getStringExtra("ds");
        dtw = getIntent().getStringExtra("dtw");
        mot = getIntent().getStringExtra("mot");

        pmh = getIntent().getStringExtra("pmh");
        ho = getIntent().getStringExtra("ho");
        uoh = getIntent().getStringExtra("uoh");
        coh = getIntent().getStringExtra("coh");
        msw = getIntent().getStringExtra("msw");
        msl = getIntent().getStringExtra("msl");
        msf = getIntent().getStringExtra("msf");
        as = getIntent().getStringExtra("as");
        lsf = getIntent().getStringExtra("lsf");
        dnh = getIntent().getStringExtra("dnh");
        lsu = getIntent().getStringExtra("lsu");
        lor = getIntent().getStringExtra("lor");

        cf = getIntent().getStringExtra("cf");
        nph = getIntent().getStringExtra("nph");
        nmc = getIntent().getStringExtra("nmc");
        nm = getIntent().getStringExtra("nm");
        nf = getIntent().getStringExtra("nf");
        nc = getIntent().getStringExtra("nc");
        nep = getIntent().getStringExtra("nep");

        tb = getIntent().getStringExtra("tb");
        tg = getIntent().getStringExtra("tg");
        ceb = getIntent().getStringExtra("ceb");
        aab = getIntent().getStringExtra("aab");

        rfm = getIntent().getStringExtra("rfm");
        fw = getIntent().getStringExtra("fw");
        ft = getIntent().getStringExtra("ft");
        String enu_name = getIntent().getStringExtra("enu_name");

        h_uid.setText(head_uid);
        emp_id.setText(enu_id);

        final_sub.setOnClickListener(v -> {
            SubAuth subAuth = new SubAuth(this);
            subAuth.execute(head_uid, enu_id);

            HeadInfo headInfo = new HeadInfo(this);
            headInfo.execute(head_uid, education, fos, mt, foo, hc, ds, dtw, mot);

            HomeInfo homeInfo = new HomeInfo(this);
            homeInfo.execute(head_uid, pmh, ho, uoh, coh, msw, msl, msf, as, lsf, dnh, lsu, lor);

            FamilyInfo familyInfo = new FamilyInfo(this);
            familyInfo.execute(head_uid, cf, nph, nmc, nm, nf, nc, nep);

            FertilityInfo fertilityInfo = new FertilityInfo(this);
            fertilityInfo.execute(head_uid, tb, tg, ceb, aab);

            MigrationInfo migrationInfo = new MigrationInfo(this);
            migrationInfo.execute(head_uid, rfm, fw, ft);

            Toast.makeText(getApplicationContext(), " DATA ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), home.class);
            i.putExtra("enu_id",enu_id);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        });

        cb1.setOnClickListener(v -> {
            if (cb1.isChecked()) {
                final_sub.setVisibility(View.VISIBLE);
            } else {
                final_sub.setVisibility(View.INVISIBLE);
            }
        });
    }

    class SubAuth extends AsyncTask<String, Void, String> {
        Context ctx;

        SubAuth(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String sub_url = "http://172.20.10.2/API/sub_auth.php";
            head_uid = params[0];
            enu_id = params[1];
            try {
                URL url = new URL(sub_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("head_uid", "UTF-8") + "=" + URLEncoder.encode(head_uid, "UTF-8") + "&" +
                        URLEncoder.encode("enu_id", "UTF-8") + "=" + URLEncoder.encode(enu_id, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class HeadInfo extends AsyncTask<String, Void, String> {
        Context ctx;

        HeadInfo(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String head_url = "http://172.20.10.2/API/head_info.php";
            head_uid = params[0];
            education = params[1];
            fos = params[2];
            mt = params[3];
            foo = params[4];
            hc = params[5];
            ds = params[6];
            dtw = params[7];
            mot = params[8];
            try {
                URL url = new URL(head_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("head_uid", "UTF-8") + "=" + URLEncoder.encode(head_uid, "UTF-8") + "&" +
                        URLEncoder.encode("head_edu", "UTF-8") + "=" + URLEncoder.encode(education, "UTF-8") + "&" +
                        URLEncoder.encode("head_fos", "UTF-8") + "=" + URLEncoder.encode(fos, "UTF-8") + "&" +
                        URLEncoder.encode("head_mt", "UTF-8") + "=" + URLEncoder.encode(mt, "UTF-8") + "&" +
                        URLEncoder.encode("head_of", "UTF-8") + "=" + URLEncoder.encode(foo, "UTF-8") + "&" +
                        URLEncoder.encode("head_ds", "UTF-8") + "=" + URLEncoder.encode(hc, "UTF-8") + "&" +
                        URLEncoder.encode("head_hc", "UTF-8") + "=" + URLEncoder.encode(ds, "UTF-8") + "&" +
                        URLEncoder.encode("head_dtw", "UTF-8") + "=" + URLEncoder.encode(dtw, "UTF-8") + "&" +
                        URLEncoder.encode("head_mot", "UTF-8") + "=" + URLEncoder.encode(mot, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class HomeInfo extends AsyncTask<String, Void, String> {
        Context ctx;

        HomeInfo(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String home_url = "http://172.20.10.2/API/home_info.php";
            head_uid = params[0];
            pmh = params[1];
            ho = params[2];
            uoh = params[3];
            coh = params[4];
            msw = params[5];
            msl = params[6];
            msf = params[7];
            as = params[8];
            lsf = params[9];
            dnh = params[10];
            lsu = params[11];
            lor = params[12];
            try {
                URL url = new URL(home_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("head_uid", "UTF-8") + "=" + URLEncoder.encode(head_uid, "UTF-8") + "&" +
                        URLEncoder.encode("home_moh", "UTF-8") + "=" + URLEncoder.encode(pmh, "UTF-8") + "&" +
                        URLEncoder.encode("home_own", "UTF-8") + "=" + URLEncoder.encode(ho, "UTF-8") + "&" +
                        URLEncoder.encode("home_use", "UTF-8") + "=" + URLEncoder.encode(uoh, "UTF-8") + "&" +
                        URLEncoder.encode("home_con", "UTF-8") + "=" + URLEncoder.encode(coh, "UTF-8") + "&" +
                        URLEncoder.encode("home_sdw", "UTF-8") + "=" + URLEncoder.encode(msw, "UTF-8") + "&" +
                        URLEncoder.encode("home_sol", "UTF-8") + "=" + URLEncoder.encode(msl, "UTF-8") + "&" +
                        URLEncoder.encode("home_scf", "UTF-8") + "=" + URLEncoder.encode(msf, "UTF-8") + "&" +
                        URLEncoder.encode("home_sanit", "UTF-8") + "=" + URLEncoder.encode(as, "UTF-8") + "&" +
                        URLEncoder.encode("home_lack_sanit", "UTF-8") + "=" + URLEncoder.encode(lsf, "UTF-8") + "&" +
                        URLEncoder.encode("home_dist_health", "UTF-8") + "=" + URLEncoder.encode(dnh, "UTF-8") + "&" +
                        URLEncoder.encode("home_lor", "UTF-8") + "=" + URLEncoder.encode(lsu, "UTF-8") + "&" +
                        URLEncoder.encode("home_cur_dur", "UTF-8") + "=" + URLEncoder.encode(lor, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class FamilyInfo extends AsyncTask<String, Void, String> {
        Context ctx;

        FamilyInfo(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String family_url = "http://172.20.10.2/API/family_info.php";
            head_uid = params[0];
            cf = params[1];
            nph = params[2];
            nmc = params[3];
            nm = params[4];
            nf = params[5];
            nc = params[6];
            nep = params[7];
            try {
                URL url = new URL(family_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("head_uid", "UTF-8") + "=" + URLEncoder.encode(head_uid, "UTF-8") + "&" +
                        URLEncoder.encode("family_nop", "UTF-8") + "=" + URLEncoder.encode(cf, "UTF-8") + "&" +
                        URLEncoder.encode("family_nmc", "UTF-8") + "=" + URLEncoder.encode(nph, "UTF-8") + "&" +
                        URLEncoder.encode("family_nom", "UTF-8") + "=" + URLEncoder.encode(nmc, "UTF-8") + "&" +
                        URLEncoder.encode("family_nof", "UTF-8") + "=" + URLEncoder.encode(nm, "UTF-8") + "&" +
                        URLEncoder.encode("family_noc", "UTF-8") + "=" + URLEncoder.encode(nf, "UTF-8") + "&" +
                        URLEncoder.encode("family_noe", "UTF-8") + "=" + URLEncoder.encode(nc, "UTF-8") + "&" +
                        URLEncoder.encode("family_cof", "UTF-8") + "=" + URLEncoder.encode(nep, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class FertilityInfo extends AsyncTask<String, Void, String> {
        Context ctx;

        FertilityInfo(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String fertility_url = "http://172.20.10.2/API/fertility_info.php";
            head_uid = params[0];
            tb = params[1];
            tg = params[2];
            ceb = params[3];
            aab = params[4];
            try {
                URL url = new URL(fertility_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("head_uid", "UTF-8") + "=" + URLEncoder.encode(head_uid, "UTF-8") + "&" +
                        URLEncoder.encode("fertility_nob", "UTF-8") + "=" + URLEncoder.encode(tb, "UTF-8") + "&" +
                        URLEncoder.encode("fertility_nog", "UTF-8") + "=" + URLEncoder.encode(tg, "UTF-8") + "&" +
                        URLEncoder.encode("fertility_noeb", "UTF-8") + "=" + URLEncoder.encode(ceb, "UTF-8") + "&" +
                        URLEncoder.encode("fertility_avg_fb", "UTF-8") + "=" + URLEncoder.encode(aab, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class MigrationInfo extends AsyncTask<String, Void, String> {
        Context ctx;

        MigrationInfo(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String migration_url = "http://172.20.10.2/API/migration_info.php";
            head_uid = params[0];
            rfm = params[1];
            fw = params[2];
            ft = params[3];
            try {
                URL url = new URL(migration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("head_uid", "UTF-8") + "=" + URLEncoder.encode(head_uid, "UTF-8") + "&" +
                        URLEncoder.encode("migration_reason", "UTF-8") + "=" + URLEncoder.encode(rfm, "UTF-8") + "&" +
                        URLEncoder.encode("migration_where", "UTF-8") + "=" + URLEncoder.encode(fw, "UTF-8") + "&" +
                        URLEncoder.encode("migration_place", "UTF-8") + "=" + URLEncoder.encode(ft, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}