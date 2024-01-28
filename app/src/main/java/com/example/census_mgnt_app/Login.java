package com.example.census_mgnt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText enu_id_Et, enu_pass_Et;
    Button login;
    String enu_id, enu_pass;
    String e_id,e_name,e_address,e_gender,e_dob,e_cast,mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enu_id_Et = findViewById(R.id.usernameET);
        enu_pass_Et = findViewById(R.id.passET);
        login = findViewById(R.id.btn_login);
    }

    public void userLogin(View view) {
        enu_id = enu_id_Et.getText().toString();
        enu_pass = enu_pass_Et.getText().toString();
        //String method = "login";
        LoginAPI backgroundTask = new LoginAPI(this);
        backgroundTask.execute(enu_id, enu_pass);

        EnuDataAPI enuDataAPI = new EnuDataAPI(this);
        enuDataAPI.execute(enu_id);
    }
    class LoginAPI extends AsyncTask<String, Void, String> {

        Context ctx;

        LoginAPI(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String login_url = "http://172.20.10.2/API/login.php";
            //String method = params[0];

            String enu_id = params[0];
            String enu_pass = params[1];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("enu_id", "UTF-8") + "=" + URLEncoder.encode(enu_id, "UTF-8") + "&" +
                        URLEncoder.encode("enu_pass", "UTF-8") + "=" + URLEncoder.encode(enu_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                String LoginResponse = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    LoginResponse += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return LoginResponse;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void getinfoenum(String info) {
            int max = info.length();
            mob = info.substring(max - 10, max);
            String id = info.substring(0, max - 10);

            Toast.makeText(getApplicationContext(), " LOGIN SUCCESS ", Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), mob, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            if (enu_id_Et.getText().toString().equals("") && enu_pass_Et.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "ENTER EMPTY DETAILS", Toast.LENGTH_LONG).show();
            } else {
                if (s.equals("Error")) {
                    Toast.makeText(getApplicationContext(), "ENTER VALID LOGIN  DETAILS", Toast.LENGTH_LONG).show();
                } else {
                    getinfoenum(s);
                }
            }
        }
    }

    class EnuDataAPI extends AsyncTask<String, Void, String> {
        Context ctx;

        EnuDataAPI(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String login_url = "http://172.20.10.2/API/enuData.php";

            String enu_id = params[0];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("enu_id", "UTF-8") + "=" + URLEncoder.encode(enu_id, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, StandardCharsets.ISO_8859_1));
                String enuData = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    enuData += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return enuData;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void getinfoenum1(String info){
            int max = info.length();
            char ch = ':';
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < info.length(); i++){
                if(info.charAt(i) == ch){
                    list.add(i);
                }
            }
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++){
                arr[i] = list.get(i);}
            e_id = info.substring(arr[0]+1,arr[1]-5);
            e_name = info.substring(arr[1]+1,arr[2]-7);
            e_address = info.substring(arr[2]+1,arr[3]-6);
            e_gender = info.substring(arr[3]+1,arr[4]-3);
            e_dob = info.substring(arr[4]+1,arr[5]-4);
            e_cast = info.substring(arr[list.size()-1]+1,max);

            Intent i = new Intent(ctx, EnuDetails.class);
            i.putExtra("enu_id", enu_id);
            i.putExtra("enu_mob",mob);
            i.putExtra("e_name",e_name);
            i.putExtra("e_gender",e_gender);
            i.putExtra("e_dob",e_dob);
            startActivity(i);
        }

        @Override
        protected void onPostExecute(String s) {
            getinfoenum1(s);
        }
    }
}