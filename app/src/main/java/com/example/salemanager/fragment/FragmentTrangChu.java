package com.example.salemanager.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.salemanager.R;
import com.example.salemanager.fragment.add.AddHD;
import com.example.salemanager.fragment.add.AddKH;
import com.example.salemanager.fragment.add.AddSp;
import com.example.salemanager.fragment.objectfragment.ObjectSP;
import com.example.salemanager.fragment.recycfragment.RecycleAdapterTrangChu;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import apdapter.SlideShowbannerAdapter;
import me.relex.circleindicator.CircleIndicator3;
import model.SlideShowBanner;


public class FragmentTrangChu extends Fragment {
    String img;
    RecyclerView recyclerView, recyclerView1;

    ArrayList<ObjectSP> dataList = new ArrayList<ObjectSP>();
    ArrayList<ObjectSP> dataList1 = new ArrayList<ObjectSP>();


    SearchView searchView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("comnication").child("product");
    DatabaseReference reference1 = database.getReference("comnication").child("product");
    private ViewPager2 mViewPager2; // Khai bao doi tuong viewpager 2
    private CircleIndicator3 mCircleIndicator3; // khai bao doi tuong circle indicator 3
    ImageButton acer,asus,dell,think,mac,hp,lenovo,msi;
    private List<SlideShowBanner> mslidSlideShowBanners; // khai bao 1 doi tuong list slideshow
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mrunRunnable = new Runnable() {
        @Override
        public void run() {
            int currentposotion = mViewPager2.getCurrentItem();
            if (currentposotion == mslidSlideShowBanners.size() - 1) { // neu =  anh cuoi cung
                mViewPager2.setCurrentItem(0); // mview pager 2 = anh dau tien

            } else {
                mViewPager2.setCurrentItem(currentposotion + 1); // nguoc lai thi chuyen den anh tiep theo
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.rcv_spBanChay);
        recyclerView1 = view.findViewById(R.id.rcv_spMoi);
        mViewPager2 = view.findViewById(R.id.viewpager_2);// anh xa viewpager 2
        mCircleIndicator3 = view.findViewById(R.id.circle_indicator_3);
        acer = view.findViewById(R.id.btnacer);
        asus = view.findViewById(R.id.btnasus);
        dell = view.findViewById(R.id.btndell);
        think = view.findViewById(R.id.btnthink);
        mac = view.findViewById(R.id.btnmac);
        hp = view.findViewById(R.id.btnhp);
        lenovo = view.findViewById(R.id.btnlenovo);
        msi = view.findViewById(R.id.btnmsi);

        acer.setOnClickListener(view1 -> {

        });
        asus.setOnClickListener(view1 -> {

        });
        dell.setOnClickListener(view1 -> {

        });
        think.setOnClickListener(view1 -> {

        });
        mac.setOnClickListener(view1 -> {

        });
        hp.setOnClickListener(view1 -> {

        });
        lenovo.setOnClickListener(view1 -> {

        });
        msi.setOnClickListener(view1 -> {

        });

        searchView = view.findViewById(R.id.search_View);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(), 2);
        recyclerView1.setLayoutManager(layoutManager1);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView1.addItemDecoration(dividerItemDecoration);

        mslidSlideShowBanners = getListSiSlideShowBanners(); // gan doi tuong list slideshow vao ham add list slideshow
        if (searchView != null) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    seach(s);
                    return true;
                }
            });
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                for (DataSnapshot snapshot : snap.getChildren()) {
                    ObjectSP data = snapshot.getValue(ObjectSP.class);
                    dataList.add(data);

                }
                RecycleAdapterTrangChu adapter = new RecycleAdapterTrangChu(dataList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                for (DataSnapshot snapshot : snap.getChildren()) {
                    ObjectSP data = snapshot.getValue(ObjectSP.class);
                        dataList1.add(data);


                }
                RecycleAdapterTrangChu adapter = new RecycleAdapterTrangChu(dataList1);
                recyclerView1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        int spanCount = 2; // số cột trong Gridview


//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
        SlideShowbannerAdapter slideApdapter = new SlideShowbannerAdapter(mslidSlideShowBanners); // khoi tao doi tuong slideshown adapter
        mViewPager2.setAdapter(slideApdapter); // set adapter cho pageview 2
        mCircleIndicator3.setViewPager(mViewPager2); // lien ket viewpager 2 voi indicator
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // lang nghe su kien chuyen page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mrunRunnable); // neu runable cu dang chay thi lan mo app tiep theo se xoa di va tao moi
                mHandler.postDelayed(mrunRunnable, 3000); //set handle moi va set thoi gian chuyen anh

            }
        });


        // Hiển thị fragment A mặc định khi mở ứng dụng

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
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

    private void seach(String str) {

        ArrayList<ObjectSP> mylist = new ArrayList<>();
        for (ObjectSP nt : mylist) {
            if (nt.getNameSp().toLowerCase().contains(str.toLowerCase())) {
                mylist.add(nt);
            }
        }
        RecycleAdapterTrangChu myAdapter = new RecycleAdapterTrangChu(mylist);
        recyclerView1.setAdapter(myAdapter);
    }

    private List<SlideShowBanner> getListSiSlideShowBanners() {
        List<SlideShowBanner> list = new ArrayList<>();
        list.add(new SlideShowBanner(R.drawable.slideshow1));
        list.add(new SlideShowBanner(R.drawable.slideshow_10));
        list.add(new SlideShowBanner(R.drawable.slideshow_4));
        list.add(new SlideShowBanner(R.drawable.slideshow_14));

        list.add(new SlideShowBanner(R.drawable.slideshow1));

        list.add(new SlideShowBanner(R.drawable.slideshow_10));
        list.add(new SlideShowBanner(R.drawable.slideshow_4));
        list.add(new SlideShowBanner(R.drawable.slideshow_14));

        return list;
    }


}

