package com.example.salemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salemanager.databinding.FragmentTrangChuBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView tvsignup, tvforgot;
    EditText edtphone, edtpass;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://salemanager-2000f-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("user");

    private final int RECORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        tvsignup = findViewById(R.id.tv_signUp);
        edtphone = findViewById(R.id.ed_phone);
        edtpass = findViewById(R.id.ed_password);
        tvforgot = findViewById(R.id.quenmk);
        //
        int permisstion_write_storage = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permisstion_read_storage = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permisstion_camera = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        int permisstion_sendsms = ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS);
        if (permisstion_camera != PackageManager.PERMISSION_GRANTED || permisstion_read_storage != PackageManager.PERMISSION_GRANTED || permisstion_sendsms != PackageManager.PERMISSION_GRANTED || permisstion_write_storage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.VIBRATE, Manifest.permission.READ_EXTERNAL_STORAGE}, RECORD);
        }
        tvforgot.setOnClickListener(v -> {
            startActivity(new Intent(this, AddSanPham.class));
        });
        btnLogin.setOnClickListener(v -> {

            String phone = edtphone.getText().toString();
            String pass = edtpass.getText().toString();
            if (isNetworkConnected(this)) {
                // Thiết bị đang kết nối mạng
                if (phone.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this, "Please,Input pass or phone", Toast.LENGTH_SHORT).show();
                } else {
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phone)) {
                                String getPass = snapshot.child(phone).child("pass").getValue(String.class);
                                if (getPass.equals(pass)) {
                                    Toast.makeText(Login.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, Home.class);// Truyền một Boolean
                                    intent.putExtra("phone", phone);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Input pass fail", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Login.this, "Input phone fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
             } else {
                Toast.makeText(this, "Please,Connect Internet", Toast.LENGTH_SHORT).show();

            }
        });

        tvsignup.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Singup.class));
            finish();
        });


    }

    private boolean isNetworkConnected(Context connect) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) connect.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!= null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }


}

