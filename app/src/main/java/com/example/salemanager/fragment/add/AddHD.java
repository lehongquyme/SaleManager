package com.example.salemanager.fragment.add;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salemanager.R;
import com.example.salemanager.fragment.FragmentHoaDon;
import com.example.salemanager.fragment.objectfragment.ObjectSP;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddHD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddHD extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://salemanager-2000f-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("comnication").child("bill");
    DatabaseReference reference = database.getReference("comnication").child("product");

    EditText maHD,ngaynhapHD,soluongHD,dongiaHD;
    TextView thanhtienHD;
    ImageView btnsave,btnrefresh;
    String idHD,dateHD,tt,totalHD="",selectedItem = "";
    int amongHD=0;
    int priceHD=0;
    ArrayList<String> dataList = new ArrayList<String>();

    public AddHD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddHD.
     */
    // TODO: Rename and change types and number of parameters
    public static AddHD newInstance(String param1, String param2) {
        AddHD fragment = new AddHD();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_hd, container, false);
        Spinner mySpinner = view.findViewById(R.id.spinnerHD);
        maHD= view.findViewById(R.id.maHD);
        ngaynhapHD= view.findViewById(R.id.ngaynhap);
        soluongHD= view.findViewById(R.id.soluong);
        dongiaHD= view.findViewById(R.id.dongia);
        btnsave= view.findViewById(R.id.btnsaveHD);
        btnrefresh= view.findViewById(R.id.btnrefreshHD);
        thanhtienHD= view.findViewById(R.id.thanhtien);

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            @SuppressLint("NewApi")
            public void onDataChange(@NonNull DataSnapshot snap) {
                for (DataSnapshot snapshot : snap.getChildren()) {
                    ObjectSP data = snapshot.getValue(ObjectSP.class);
                    dataList.add( data.getTenSanPham());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dataList);
                mySpinner.setAdapter(adapter);
                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Xử lý khi không có giá trị nào được chọn
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        btnsave.setOnClickListener(view1 -> {
            createHoaDon();
        });




        return  view;
    }



    void createHoaDon() {
        idHD = maHD.getText().toString();
        dateHD = ngaynhapHD.getText().toString();

        totalHD = thanhtienHD.getText().toString();
        amongHD = Integer.parseInt(soluongHD.getText().toString());
        priceHD = Integer.parseInt(dongiaHD.getText().toString());
        tt = String.valueOf(amongHD*priceHD);


        if (idHD.isEmpty() || dateHD.isEmpty()  ) {
            Toast.makeText(getContext(), "Please input inforMain", Toast.LENGTH_SHORT).show();
        } else {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(idHD)) {
                        Toast.makeText(getContext(), "" +
                                "Name Bill is already", Toast.LENGTH_SHORT).show();
                    } else {
                        myRef.child(idHD).child("idHD").setValue(idHD);

                        myRef.child(idHD).child("dateHD").setValue(dateHD);
                        myRef.child(idHD).child("hangHD").setValue(selectedItem);
                        myRef.child(idHD).child("amongHD").setValue(amongHD);
                        myRef.child(idHD).child("priceHD").setValue(priceHD);
                        myRef.child(idHD).child("totalHD").setValue(tt);



                        getFragmentManager().popBackStack();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}