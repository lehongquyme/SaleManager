package com.example.salemanager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePass extends AppCompatActivity {
    Button agree;
    CheckBox showPasswordCheckBox;
    EditText repasswordEditText,passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         passwordEditText = findViewById(R.id.newpassword);
         repasswordEditText = findViewById(R.id.renewpassword);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
//        showPasswordCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
////            if (b) {
////                // Hiển thị password
////                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
////                passwordEditText.setTransformationMethod(null);
////                repasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
////                repasswordEditText.setTransformationMethod(null);
////            } else {
////                // Ẩn password
////                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
////                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
////                repasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
////                repasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
////            }
//        });


}

    @Override
    protected void onStart() {
        super.onStart();
        agree = findViewById(R.id.agree);
        showPasswordCheckBox = findViewById(R.id.showPasswordCheckBox);
        String pass = passwordEditText.getText().toString();

        agree.setOnClickListener(view -> {
            Intent intent = getIntent();
            String phone = intent.getStringExtra("phone");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("user").child(phone).child("pass");
            databaseReference.setValue(pass);
            Intent intent1 = new Intent(ChangePass.this, Home.class);// Truyền một Boolean
            intent1.putExtra("phone", phone);
            startActivity(intent1);
            finish();





        });
    }
}