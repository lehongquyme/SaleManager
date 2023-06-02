package com.example.salemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otpsms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsms);
        TextView tv_rules;
        final EditText inputphone = findViewById(R.id.inputphone);
        Button btnsendOTP = findViewById(R.id.sendOTP);
        tv_rules = findViewById(R.id.tv_rule);
        ImageView img_rule = findViewById(R.id.img_rule);

        img_rule.setOnClickListener(view -> {
            if (tv_rules.getVisibility() == View.GONE) {
                tv_rules.setVisibility(View.VISIBLE);
            } else {
                tv_rules.setVisibility(View.GONE);

            }
        });
        FirebaseApp.initializeApp(this);
        btnsendOTP.setOnClickListener(view -> {

                if (!inputphone.getText().toString().trim().isEmpty()){
                    if ((inputphone.getText().toString().trim()).length()==10)
                    {

                        btnsendOTP.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+84" + inputphone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                Otpsms.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        btnsendOTP.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        btnsendOTP.setVisibility(View.VISIBLE);
                                        Toast.makeText(Otpsms.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        btnsendOTP.setVisibility(View.VISIBLE);

                                        Intent intent=new Intent(getApplicationContext(),Verify.class);
                                        intent.putExtra("mobile",inputphone.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);

                                    }
                                }

                        );


                    }else {
                        Toast.makeText(Otpsms.this, "Nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(Otpsms.this, "Nhập vào số điện thoại", Toast.LENGTH_SHORT).show();
                }



        });
    }
}