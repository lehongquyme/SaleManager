package com.example.salemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.salemanager.fragment.FragmentHoaDon;
import com.example.salemanager.fragment.FragmentThongKe;
import com.example.salemanager.fragment.FragmentTrangChu;
import com.example.salemanager.fragment.objectfragment.ObjectSP;
import com.example.salemanager.fragment.objectfragment.ObjectUser;
import com.example.salemanager.fragment.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Base64;

public class Home extends AppCompatActivity {

    ActionBar toolbar;
    private long Pressed;
    Toast mToast;
    ImageView imageUser;
    String phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        Read();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    new FragmentTrangChu()).commit();
        }

        addControl();
        addEvent();

        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        FragmentProfile fragment = new FragmentProfile();
        fragment.setArguments(bundle);

    } private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.trangchu:
                            selectedFragment = new FragmentTrangChu();
                            break;
                        case R.id.hoadon:
                            selectedFragment = new FragmentHoaDon();
                            break;
                        case R.id.thongke:
                            selectedFragment = new FragmentThongKe();
                            break;
                        case  R.id.profile:
                            selectedFragment = new FragmentProfile();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                            selectedFragment).commit();

                    return true;
                }
            };



    private void addEvent() {
    }

    private void addControl() {
        toolbar = getSupportActionBar();
    }
    @Override
    public void onBackPressed() {
        if (Pressed + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            moveTaskToBack(true);
        } else {
            mToast = Toast.makeText(getApplicationContext(), "ấn 2 lần để thoát ứng dụng", Toast.LENGTH_SHORT);
            mToast.show();
        }
        Pressed = System.currentTimeMillis();
    }

    private void Read() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");


        reference.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            @SuppressLint("NewApi")
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                ObjectUser infor = snapshot.getValue(ObjectUser.class);

//                emailUser.setText(infor.getGmail());
//                nameUser.setText(infor.getFullname());
//                phoneUser.setText(infor.getPhone());

//                byte[] decodeString = Base64.getDecoder().decode(infor.getImage());
//                Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                im.setImageBitmap(decodeByte);
//                bytesToImage(imageUser, infor.getImage());
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

            }
        });
    }
    private void bytesToImage(ImageView imageView, String base64String) {
        if (!base64String.isEmpty()) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(base64String);
            }
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            Glide.with(this).load(decodedByte).into(imageView);
        }
    }
}
