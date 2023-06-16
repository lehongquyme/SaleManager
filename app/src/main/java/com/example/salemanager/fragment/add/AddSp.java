package com.example.salemanager.fragment.add;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.salemanager.Login;
import com.example.salemanager.R;
import com.example.salemanager.Singup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSp extends Fragment {
    private static final int PICK_IMAGE = 1;
    EditText maSp, tenSp, giaTien, cpu, ram, rom, hdh, manHinh, cardMh, dlp, tl, mt;
    String maSanPham, tenSanPham, giaTienSanPham, cpuSanPham, ramSanPham, romSanPham, hdhSanPham, manHinhSanPham, cardMhSanPham, dlpSanPham, tlSanPham, mtSanPham;
    String base64;
    ImageView imageView, imgSave;
    private Uri mImageUri;
    RadioButton radioButtonCu, radioButtonLikeNew, radioButtonNew, radioButtonHet, radioButtonCon;
    RadioGroup radioGroup, radioGroup1;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://salemanager-2000f-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("comnication").child("product");
    public String trangthai = "", tinhtrang = "", selectedItem = "";
    String[] dataList = {"Dell", "Hp", "Asus", "Msi", "Lenovo", "Razer", "Acer", "Microsoft", "Macbook"};

    public AddSp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSp.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSp newInstance(String param1, String param2) {
        AddSp fragment = new AddSp();
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

        View view = inflater.inflate(R.layout.fragment_add_sp, container, false);
        maSp = view.findViewById(R.id.maSp);
        tenSp = view.findViewById(R.id.nameSp);
        giaTien = view.findViewById(R.id.giatien);
        cpu = view.findViewById(R.id.cpu);
        ram = view.findViewById(R.id.ram);
        rom = view.findViewById(R.id.rom);
        hdh = view.findViewById(R.id.hedieuhanh);
        manHinh = view.findViewById(R.id.manhinh);
        cardMh = view.findViewById(R.id.cardmanhinh);
        dlp = view.findViewById(R.id.dungluongpin);
        tl = view.findViewById(R.id.trongluong);
        mt = view.findViewById(R.id.mota);
        imageView = view.findViewById(R.id.id_addspimg);
        imgSave = view.findViewById(R.id.imgSave);
        radioButtonCu = view.findViewById(R.id.rdoCu);
        radioButtonLikeNew = view.findViewById(R.id.rdoLikeNew);
        radioButtonNew = view.findViewById(R.id.rdoNew);
        radioGroup = view.findViewById(R.id.groupTinhTrang);
        radioButtonHet = view.findViewById(R.id.rdoHet);
        radioButtonCon = view.findViewById(R.id.rdoCon);
        radioGroup1 = view.findViewById(R.id.groupTrangThai);
        Toast.makeText(getContext(), radioButtonNew.getText(), Toast.LENGTH_SHORT).show();

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {

            doOnDifficultyLevelChanged(radioGroup1, i);
        });
        radioGroup1.setOnCheckedChangeListener((radioGroup1, i) -> {

            doOnDifficultyLevelChanged1(radioGroup1, i);
        });

        Spinner mySpinner = view.findViewById(R.id.spinner);
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


        imageView.setOnClickListener(view1 -> {
            openFileChooser();
        });
        imgSave.setOnClickListener(view1 -> {
            createProduct();
        });

        return view;
    }

    @SuppressLint({"NewApi", "LocalSuppress"})
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            byte[] imgByte = getByteUri(getContext(), mImageUri);
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
        startActivityForResult(intent, PICK_IMAGE);
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

    void createProduct() {
        maSanPham = maSp.getText().toString();
        tenSanPham = tenSp.getText().toString();
        giaTienSanPham = giaTien.getText().toString();
        cpuSanPham = cpu.getText().toString();
        ramSanPham = ram.getText().toString();
        romSanPham = rom.getText().toString();
        hdhSanPham = hdh.getText().toString();
        manHinhSanPham = manHinh.getText().toString();
        cardMhSanPham = cardMh.getText().toString();
        dlpSanPham = dlp.getText().toString();
        tlSanPham = tl.getText().toString();
        mtSanPham = mt.getText().toString();


        if (maSanPham.isEmpty() || tenSanPham.isEmpty() || giaTienSanPham.isEmpty() || cpuSanPham.isEmpty() || ramSanPham.isEmpty() || romSanPham.isEmpty() || hdhSanPham.isEmpty() || manHinhSanPham.isEmpty() || cardMhSanPham.isEmpty() || dlpSanPham.isEmpty() || tlSanPham.isEmpty()
        ) {
            Toast.makeText(getContext(), "Please input inforMain", Toast.LENGTH_SHORT).show();
        } else {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(tenSanPham)) {
                        Toast.makeText(getContext(), "" +
                                "Name Product is already", Toast.LENGTH_SHORT).show();
                    } else {
                        myRef.child(tenSanPham).child("image").setValue(base64);

                        myRef.child(tenSanPham).child("maSanPham").setValue(maSanPham);
                        myRef.child(tenSanPham).child("tenSanPham").setValue(tenSanPham);
                        myRef.child(tenSanPham).child("giaTienSanPham").setValue(giaTienSanPham);
                        myRef.child(tenSanPham).child("cpuSanPham").setValue(cpuSanPham);
                        myRef.child(tenSanPham).child("ramSanPham").setValue(ramSanPham);
                        myRef.child(tenSanPham).child("romSanPham").setValue(romSanPham);
                        myRef.child(tenSanPham).child("hdhSanPham").setValue(hdhSanPham);
                        myRef.child(tenSanPham).child("manHinhSanPham").setValue(manHinhSanPham);
                        myRef.child(tenSanPham).child("cardMhSanPham").setValue(cardMhSanPham);
                        myRef.child(tenSanPham).child("dlpSanPham").setValue(dlpSanPham);
                        myRef.child(tenSanPham).child("tlSanPham").setValue(tlSanPham);
                        myRef.child(tenSanPham).child("mtSanPham").setValue(mtSanPham);
                        myRef.child(tenSanPham).child("tinhtrang").setValue(tinhtrang);
                        myRef.child(tenSanPham).child("trangthai").setValue(trangthai);
                        myRef.child(tenSanPham).child("hang").setValue(selectedItem);


                        getFragmentManager().popBackStack();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void doOnDifficultyLevelChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if (checkedRadioId == R.id.rdoCu) {
            tinhtrang = radioButtonCu.getText().toString();

        } else if (checkedRadioId == R.id.rdoLikeNew) {
            tinhtrang = radioButtonLikeNew.getText().toString();
        } else if (checkedRadioId == R.id.rdoNew) {
            tinhtrang = radioButtonNew.getText().toString();

        }
    }

    private void doOnDifficultyLevelChanged1(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if (checkedRadioId == R.id.rdoHet) {
            trangthai = radioButtonHet.getText().toString();

        } else if (checkedRadioId == R.id.rdoCon) {
            trangthai = radioButtonCon.getText().toString();
        }
    }


}
