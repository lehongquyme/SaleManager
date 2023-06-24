package com.example.salemanager.fragment.add;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.salemanager.R;
import com.example.salemanager.fragment.FragmentHoaDon;
import com.example.salemanager.fragment.FragmentProfile;
import com.example.salemanager.fragment.FragmentTrangChu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddKH#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddKH extends Fragment  {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://salemanager-2000f-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("comnication").child("client");
    EditText      tenKhachHang,ngaythangnamsinh,sdtKhachHang,dcKhachHang;
        String nameKH,dateKH,phoneKH,addressKH;
    ImageButton refresh, save;
    public AddKH() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddKH.
     */
    // TODO: Rename and change types and number of parameters
    public static AddKH newInstance(String param1, String param2) {
        AddKH fragment = new AddKH();
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
        View view = inflater.inflate(R.layout.fragment_add_kh, container, false);
        tenKhachHang = view.findViewById(R.id.nameKH);
        ngaythangnamsinh = view.findViewById(R.id.dateKH);
        sdtKhachHang = view.findViewById(R.id.phoneKH);
        dcKhachHang = view.findViewById(R.id.diachiKH);
createKhachHang();
        return view;

    }
    void createKhachHang() {
        nameKH = tenKhachHang.getText().toString();
        dateKH = ngaythangnamsinh.getText().toString();
        phoneKH= sdtKhachHang.getText().toString();
   addressKH = dcKhachHang.getText().toString();


        if (nameKH.isEmpty() || dateKH.isEmpty()  ||phoneKH.isEmpty()||addressKH.isEmpty()) {
            Toast.makeText(getContext(), "Please input inforMain", Toast.LENGTH_SHORT).show();
        } else {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phoneKH)) {
                        Toast.makeText(getContext(), "" +
                                "Name Client is already", Toast.LENGTH_SHORT).show();
                    } else {
                        myRef.child(phoneKH).child("tenKH").setValue(nameKH);

                        myRef.child(phoneKH).child("dateKH").setValue(dateKH);
                        myRef.child(phoneKH).child("phoneKH").setValue(phoneKH);
                        myRef.child(phoneKH).child("address").setValue(addressKH);



                        getFragmentManager().popBackStack();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.add(R.id.linearHD, new FragmentHoaDon());
                        transaction.addToBackStack("myFragmentStack");
                        transaction.commit();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


}