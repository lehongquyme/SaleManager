package com.example.salemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSanPham extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://salemanager-2000f-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("comnication");
    String trangthai = "",tinhtrang = "",selectedData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);

        RadioGroup radioGroup = findViewById(R.id.groupTinhTrang);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.rdoCu) {
            tinhtrang = "Cũ";
        } else if (selectedRadioButtonId == R.id.rdoLikeNew) {
            tinhtrang = "Gần Mới 99%";
        } else if (selectedRadioButtonId == R.id.rdoNew) {
            tinhtrang = "Mới";
        }
        RadioGroup radioGroup1 = findViewById(R.id.groupTrangThai);
        int selectedRadioButtonId1 = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.rdoHet) {
            trangthai = "Hết Hàng";
        } else if (selectedRadioButtonId == R.id.rdoCon) {
            trangthai = "Còn Hàng";
        }
        Spinner mySpinner = findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Dell", "Hp", "Asus","Msi","Lenovo","Razer","Acer","Microsoft"});

        mySpinner.setAdapter(myAdapter);
        if (mySpinner != null && mySpinner.getSelectedItem() != null) {
            int selectedIndex = mySpinner.getSelectedItemPosition();
            selectedData = (String) mySpinner.getItemAtPosition(selectedIndex);
        }
        Toast.makeText(this, selectedData, Toast.LENGTH_SHORT).show();

    }
}