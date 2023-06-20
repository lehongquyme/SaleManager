package com.example.salemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Singup extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    String base64;
    Button btncreate;
    TextView tv_rules;
    EditText edtname, edtpass, edtrepass, edtphone, edtgmail,edtadmin;
    ImageView imageView,img_rule;
    private Uri mImageUri;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://appsalemanager-ee563-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        btncreate = findViewById(R.id.btn_login);
        edtname = findViewById(R.id.ed_name);
        edtpass = findViewById(R.id.ed_password);
        edtrepass = findViewById(R.id.ed_repassword);
        edtphone = findViewById(R.id.ed_phone);
        edtgmail = findViewById(R.id.ed_gmail);
        edtadmin = findViewById(R.id.ed_admin);
        imageView = findViewById(R.id.id_showimg);
        img_rule = findViewById(R.id.img_rules);
        tv_rules = findViewById(R.id.tv_rules);
        img_rule.setOnClickListener(view -> {
            if(tv_rules.getVisibility() == View.GONE){
                tv_rules.setVisibility(View.VISIBLE);
            }else {
                tv_rules.setVisibility(View.GONE);

            }
        });
        btncreate.setOnClickListener(v -> {
            createAccount();

        });

        imageView.setOnClickListener(v -> {
            openFileChooser();
        });
    }
    @SuppressLint({"NewApi", "LocalSuppress"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            byte[] imgByte = getByteUri(getApplicationContext(), mImageUri);
            base64 = Base64.getEncoder().encodeToString(imgByte);
            byte[] decodeString = Base64.getDecoder().decode(base64);
            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
            imageView.setImageBitmap(decodeByte);


        }
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    static byte[] getByteUri(Context context, Uri uri) {
        InputStream inputStream = null;

        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int buffet = 1024;
            byte[] buf = new byte[buffet];
            int leng = 0;
            while ((leng = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, leng);
            }
            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    void createAccount() {

        String userName = edtname.getText().toString();
        String phone = edtphone.getText().toString();
        String gmail = edtgmail.getText().toString();
        String admin = edtadmin.getText().toString();
        String pass = edtpass.getText().toString();
        String repass = edtrepass.getText().toString();
        if (userName.isEmpty() || phone.isEmpty() || gmail.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(this, "Please input inforMain", Toast.LENGTH_SHORT).show();
        } else if (!pass.equals(repass)) {
            Toast.makeText(this, "Please input pass and repass together", Toast.LENGTH_SHORT).show();
        } else {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)) {
                        Toast.makeText(Singup.this, "Phone is already", Toast.LENGTH_SHORT).show();
                    } else {
                        myRef.child(phone).child("image").setValue(base64);

                        myRef.child(phone).child("fullname").setValue(userName);
                        myRef.child(phone).child("phone").setValue(phone);
                        myRef.child(phone).child("pass").setValue(pass);
                        myRef.child(phone).child("repass").setValue(repass);
                        myRef.child(phone).child("gmail").setValue(gmail);
                        myRef.child(phone).child("admin").setValue(admin);


                        startActivity(new Intent(Singup.this, Login.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}