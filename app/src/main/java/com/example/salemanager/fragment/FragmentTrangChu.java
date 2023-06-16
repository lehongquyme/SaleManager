package com.example.salemanager.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.salemanager.AddSanPham;
import com.example.salemanager.Home;
import com.example.salemanager.R;
import com.example.salemanager.fragment.add.AddHD;
import com.example.salemanager.fragment.add.AddKH;
import com.example.salemanager.fragment.add.AddSp;
import com.example.salemanager.fragment.objectfragment.ObjectSP;
import com.example.salemanager.fragment.objectfragment.ObjectUser;
import com.example.salemanager.fragment.recycfragment.RecycleAdapterTrangChu;
import com.google.android.material.navigation.NavigationView;
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
import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;
import model.SlideShowBanner;


public class FragmentTrangChu extends Fragment {
    String img;
    RecyclerView recyclerView;
    RecycleAdapterTrangChu adapter;
    ArrayList<ObjectSP>  list = new ArrayList<ObjectSP>();
    Button btnmenu;
    FrameLayout frameLayout;
    Fragment fragment;
    private AddSp fragmentAddSp;
    private AddKH fragmentAddKH;
    private AddHD fragmentAddHD;

    private ViewPager2 mViewPager2; // Khai bao doi tuong viewpager 2
    private CircleIndicator3 mCircleIndicator3; // khai bao doi tuong circle indicator 3
    private List<SlideShowBanner> mslidSlideShowBanners; // khai bao 1 doi tuong list slideshow
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private  Runnable mrunRunnable = new Runnable() {
        @Override
        public void run() {
            int currentposotion = mViewPager2.getCurrentItem();
            if(currentposotion == mslidSlideShowBanners.size()-1){ // neu =  anh cuoi cung
                mViewPager2.setCurrentItem(0); // mview pager 2 = anh dau tien

            }
            else {
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
        mViewPager2 = view.findViewById(R.id.viewpager_2);// anh xa viewpager 2

        mCircleIndicator3 = view.findViewById(R.id.circle_indicator_3);

        mViewPager2 = view.findViewById(R.id.viewpager_2);// anh xa viewpager 2
        mCircleIndicator3 =view.findViewById(R.id.circle_indicator_3);
        mslidSlideShowBanners = getListSiSlideShowBanners(); // gan doi tuong list slideshow vao ham add list slideshow

        SlideShowbannerAdapter slideApdapter = new SlideShowbannerAdapter(mslidSlideShowBanners); // khoi tao doi tuong slideshown adapter
        mViewPager2.setAdapter(slideApdapter); // set adapter cho pageview 2
        mCircleIndicator3.setViewPager(mViewPager2); // lien ket viewpager 2 voi indicator
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // lang nghe su kien chuyen page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mrunRunnable); // neu runable cu dang chay thi lan mo app tiep theo se xoa di va tao moi
                mHandler.postDelayed(mrunRunnable,3000); //set handle moi va set thoi gian chuyen anh

            }
        });
        fragmentAddSp = new AddSp();
        fragmentAddKH = new AddKH();
        fragmentAddHD = new AddHD();


        // Hiển thị fragment A mặc định khi mở ứng dụng

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();




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

