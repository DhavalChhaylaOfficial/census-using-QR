package com.example.census_mgnt_app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String d;
    Button scanbtn,nxtbtn;
    EditText uidET;

    TextView enutxt,uidtxt,nametxt,mobtxt,addresstxt,gendertxt,casttxt,dobtxt,dup1,txt3;

    String u_uid;
    LottieAnimationView scan,done;

    static  String uid;
    static String name;
    static String mob;
    static String address;
    static String gender;
    static String cast;
    static String date;
    static String enu_id;
    String enu_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enu_name = getIntent().getStringExtra("enu_name");

        scan = findViewById(R.id.scan);
        done = findViewById(R.id.done);

        scanbtn = findViewById(R.id.button);
        nxtbtn = findViewById(R.id.btn_nxt);

        enutxt = findViewById(R.id.enuid);
        enu_id = getIntent().getStringExtra("enu_id");

        uidET = findViewById(R.id.uidET);
        dup1 = findViewById(R.id.dup1);
        txt3 = findViewById(R.id.textView3);

        scanbtn.setOnClickListener(v -> {
            scanCode();
            txt3.setVisibility(View.INVISIBLE);
            scanbtn.setVisibility(View.INVISIBLE);
            scan.setVisibility(View.INVISIBLE);
        });

        nxtbtn.setOnClickListener(v -> {
            u_uid = uidET.getText().toString();
            UidFetchDataAPI backgroundTask = new UidFetchDataAPI(this);
            backgroundTask.execute(u_uid);

            //Toast.makeText(getApplicationContext(), "GETTING INFO", Toast.LENGTH_LONG).show();

        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() != null)
        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("QR DATA");
            d = result.getContents();
            //builder.setMessage(d);
            uidET.setText(d);
            nxtbtn.setVisibility(View.VISIBLE);
            dup1.setVisibility(View.VISIBLE);
            done.setVisibility(View.VISIBLE);
            //builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//
//                }
//            }).show();

        }
    });

    class UidFetchDataAPI extends AsyncTask<String, Void, String> {

        Context ctx;
        UidFetchDataAPI(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String uid_url = "http://172.20.10.2/API/uid.php";

            String uid = params[0];
            try {
                URL url = new URL(uid_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                String UidResponse = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    UidResponse += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return UidResponse;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void getinfohead(String info) {
            int max = info.length();
            char ch = '=';
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < info.length(); i++) {
                if (info.charAt(i) == ch) {
                    list.add(i);
                }
            }
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }

            uid = info.substring(arr[0] + 1, arr[1] - 5);
            uidtxt = findViewById(R.id.uidtxt);
            uidtxt.setText(uid);

            name = info.substring(arr[1] + 1, arr[2] - 10);
            nametxt = findViewById(R.id.nametxt);
            nametxt.setText(name);

            mob = info.substring(arr[2] + 1, arr[3] - 8);
            mobtxt = findViewById(R.id.mobtxt);
            mobtxt.setText(mob);

            address = info.substring(arr[3] + 1, arr[4] - 7);
            addresstxt = findViewById(R.id.addresstxt);
            addresstxt.setText(address);

            gender = info.substring(arr[4] + 1, arr[5] - 5);
            gendertxt = findViewById(R.id.gendertxt);
            gendertxt.setText(gender);

            cast = info.substring(arr[5] + 1, arr[6] - 11);
            casttxt = findViewById(R.id.casttxt);
            casttxt.setText(cast);

            date = info.substring(arr[list.size() - 1] + 1, max);
            dobtxt = findViewById(R.id.dobtxt);
            dobtxt.setText(date);

            Intent i = new Intent(ctx, uidData.class);
            i.putExtra("uid", uidET.getText().toString());
            i.putExtra("name", nametxt.getText().toString());
            i.putExtra("address", addresstxt.getText().toString());
            i.putExtra("cast", casttxt.getText().toString());
            i.putExtra("mob", mobtxt.getText().toString());
            i.putExtra("gender", gendertxt.getText().toString());
            i.putExtra("dob", dobtxt.getText().toString());
            i.putExtra("enu_id",enu_id);
            i.putExtra("enu_name",enu_name);
            startActivity(i);
        }

        @Override
        protected void onPostExecute(String s) {
            getinfohead(s);
            //Toast.makeText(getApplicationContext(), "FETCHING DETAILS", Toast.LENGTH_LONG).show();
        }
    }
}