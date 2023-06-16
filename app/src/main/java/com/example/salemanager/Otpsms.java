package com.example.salemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Otpsms extends AppCompatActivity {
    EditText editText;
    Button button, confirmbtn;
    //    int code;
    PinView pinView;
    String verificationId = "";
    private String code = "";
    private FirebaseAuth  mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsms);
        TextView tv_rules;


        tv_rules = findViewById(R.id.tv_rule);
        ImageView img_rule = findViewById(R.id.img_rule);

        img_rule.setOnClickListener(view -> {
            if (tv_rules.getVisibility() == View.GONE) {
                tv_rules.setVisibility(View.VISIBLE);
            } else {
                tv_rules.setVisibility(View.GONE);

            }
        });


        editText = findViewById(R.id.inputgmail);
        button = findViewById(R.id.sendOTP);
        pinView = findViewById(R.id.firstPinView);
        confirmbtn = findViewById(R.id.signup_confirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode("+84"+editText.getText().toString());
            }
        });
        confirmbtn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(pinView.getText().toString())) {
                // if the OTP text field is empty display
                // a message to user to enter OTP
                Toast.makeText(Otpsms.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
            } else {
                // if OTP field is not empty calling
                // method to verify the OTP.
                verifyCode(pinView.getText().toString());
            }
        });
    }
    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            Toast.makeText(Otpsms.this, "Mã OTP đã gửi đến số"+editText.getText().toString().trim().substring(1), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
               // edtOTP.setText(code);
                pinView.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                Toast.makeText(Otpsms.this, "Xác Thực Thành Công", Toast.LENGTH_LONG).show();
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Otpsms.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    };
    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent intent = new Intent(Otpsms.this, ChangePass.class);// Truyền một Boolean
                            intent.putExtra("phone", editText.getText().toString());
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(Otpsms.this, task.toString(), Toast.LENGTH_LONG).show();

                            // if the code is not correct then we are
                            // displaying an error message to the user.

                        }
                    }
                });
    }

}