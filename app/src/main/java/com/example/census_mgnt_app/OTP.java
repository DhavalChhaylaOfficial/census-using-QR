package com.example.census_mgnt_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
    TextView otpmob, resendTextView;
    EditText otpEditText1, otpEditText2, otpEditText3, otpEditText4, otpEditText5, otpEditText6;
    Button verifyOTPBtn;
    String otp;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpmob = findViewById(R.id.otpmob);
        resendTextView = findViewById(R.id.resendTextView);

        String enu_id = getIntent().getStringExtra("enu_id");
        String enu_mob = getIntent().getStringExtra("enu_mob");
        String enu_name = getIntent().getStringExtra("enu_name");
        otpmob.setText("OTP IS SENT ON : +91 " + enu_mob);


        otp = getIntent().getStringExtra("otp");

        verifyOTPBtn = findViewById(R.id.verifyOTPBtn);

        pb = findViewById(R.id.otpProgressBar);

        otpEditText1 = findViewById(R.id.otpEditText1);
        otpEditText2 = findViewById(R.id.otpEditText2);
        otpEditText3 = findViewById(R.id.otpEditText3);
        otpEditText4 = findViewById(R.id.otpEditText4);
        otpEditText5 = findViewById(R.id.otpEditText5);
        otpEditText6 = findViewById(R.id.otpEditText6);

        verifyOTPBtn.setOnClickListener(v -> {
            if (!otpEditText1.getText().toString().trim().isEmpty() && !otpEditText2.getText().toString().trim().isEmpty() && !otpEditText3.getText().toString().trim().isEmpty() && !otpEditText4.getText().toString().trim().isEmpty() && !otpEditText5.getText().toString().trim().isEmpty() && !otpEditText6.getText().toString().trim().isEmpty()) {
                String enterotp = otpEditText1.getText().toString() +
                        otpEditText2.getText().toString() +
                        otpEditText3.getText().toString() +
                        otpEditText4.getText().toString() +
                        otpEditText5.getText().toString() +
                        otpEditText6.getText().toString();
                if (otp != null) {
                    pb.setVisibility(View.VISIBLE);
                    verifyOTPBtn.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            otp, enterotp
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pb.setVisibility(View.GONE);
                                    verifyOTPBtn.setVisibility(View.VISIBLE);

                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(getApplicationContext(), home.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        i.putExtra("enu_id", enu_id);
                                        i.putExtra("enu_name", enu_name);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(OTP.this, "ENTER THE CORRECT OTP", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(OTP.this, "PLEASE CHECK INTERNET", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(OTP.this,"OTP VERIFIED",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(OTP.this, "PLEASE ENTER ALL NUMBERS", Toast.LENGTH_SHORT).show();
            }
        });
        EditTextWatcher();

        resendTextView.setOnClickListener(v -> {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + enu_mob,
                    60,
                    TimeUnit.SECONDS,
                    OTP.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            otp = newotp;
                            Toast.makeText(getApplicationContext(), "OTP SENDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        }
                    }
            );

        });
    }

    private void EditTextWatcher() {
        otpEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpEditText2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpEditText3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpEditText4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpEditText5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpEditText6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}