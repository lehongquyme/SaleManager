package com.example.salemanager.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.salemanager.R;
import com.example.salemanager.fragment.add.AddHD;
import com.example.salemanager.fragment.add.AddKH;
import com.example.salemanager.fragment.add.AddSp;
import com.example.salemanager.fragment.objectfragment.ObjectUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Base64;

public class FragmentProfile extends Fragment {
Button btnSp,btnKh,btnHd,btnTt,btnExit;
ImageView ImvSetting,ImvProfile;
TextView txtName,txtPhone,txtEmail;
    String number ="";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("user");
    public FragmentProfile() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            number = bundle.getString("phone");
            if (number != null) {
                Toast.makeText(getContext(), "Phone"+number, Toast.LENGTH_SHORT).show();

            }
        }

        Toast.makeText(getContext(), "Phone"+number, Toast.LENGTH_SHORT).show();
        Read();



        reference.child("0334325232").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ObjectUser infor = snapshot.getValue(ObjectUser.class);
//                userNameTextView.setText(infor.getFullname());
//                userPhoneTextView.setText(infor.getPhone());
//                userEmailTextView.setText(infor.getGmail());
//                byte[] decodeString = Base64.getDecoder().decode(infor.getImage());
//                Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                userAvatarImageView.setImageBitmap(decodeByte);
//                bytesToImage(userAvatarImageView, infor.getImage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSp = view.findViewById(R.id.sanphamprofile);
        btnKh = view.findViewById(R.id.khachhangprofile);
        btnHd = view.findViewById(R.id.hoadonprofile);
        btnTt = view.findViewById(R.id.thongtinprofile);
        btnExit = view.findViewById(R.id.exit);
        txtName = view.findViewById(R.id.name);
        txtPhone = view.findViewById(R.id.phone);
        txtEmail = view.findViewById(R.id.email);
        ImvSetting = view.findViewById(R.id.setting);
        ImvProfile = view.findViewById(R.id.imginfor);

        btnSp.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.linearProfile, new AddSp());
            transaction.addToBackStack("myFragmentStack");
            transaction.commit();
        });

        btnHd.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.linearProfile, new AddHD());
            transaction.addToBackStack("myFragmentStack");
            transaction.commit();
        });
        btnKh.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.linearProfile, new AddKH());
            transaction.addToBackStack("myFragmentStack");
            transaction.commit();
        });
        btnTt.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.linearProfile, new FragmentThongTin());
            transaction.addToBackStack("myFragmentStack");
            transaction.commit();
        });
        btnExit.setOnClickListener(view1 -> {
        System.exit(0);
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void Read() {


        reference.child("0334325232").addValueEventListener(new ValueEventListener() {
            @Override
            @SuppressLint("NewApi")
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ObjectUser infor = snapshot.getValue(ObjectUser.class);
                txtName.setText(infor.getFullname());
                txtPhone.setText(infor.getPhone());
                txtEmail.setText(infor.getGmail());

                byte[] decodeString = Base64.getDecoder().decode(infor.getImage());
                Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                ImvProfile.setImageBitmap(decodeByte);
                bytesToImage(ImvProfile, infor.getImage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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