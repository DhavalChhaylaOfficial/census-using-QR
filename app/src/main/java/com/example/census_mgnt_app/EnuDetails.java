package com.example.census_mgnt_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnuDetails extends AppCompatActivity {
    TextView e_id, e_name, e_mob, e_gender, e_dob;
    Button vrfy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enu_details);

        e_id = findViewById(R.id.e_id);
        e_name = findViewById(R.id.e_name);
        e_mob = findViewById(R.id.e_mob);
        e_gender = findViewById(R.id.e_gender);
        e_dob = findViewById(R.id.e_dob);

        vrfy = findViewById(R.id.vrfy);

        String enu_id = getIntent().getStringExtra("enu_id");
        String enu_mob = getIntent().getStringExtra("enu_mob");
        String enu_name = getIntent().getStringExtra("e_name");
        String enu_gender = getIntent().getStringExtra("e_gender");
        String enu_dob = getIntent().getStringExtra("e_dob");


        e_id.setText(enu_id);
        e_name.setText(enu_name);
        e_mob.setText(enu_mob);
        e_gender.setText(enu_gender);
        e_dob.setText(enu_dob);

        vrfy.setOnClickListener(v -> {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + enu_mob,
                    60,
                    TimeUnit.SECONDS,
                    EnuDetails.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(EnuDetails.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            Intent i = new Intent(getApplicationContext(), OTP.class);
                            i.putExtra("enu_id",enu_id);
                            i.putExtra("enu_mob",enu_mob);
                            i.putExtra("enu_name",enu_name);
                            i.putExtra("otp",otp);
                            startActivity(i);
                        }
                    }
            );

        });
    }
}