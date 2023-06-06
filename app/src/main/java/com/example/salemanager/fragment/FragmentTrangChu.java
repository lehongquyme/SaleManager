package com.example.salemanager.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.salemanager.R;
import com.example.salemanager.nav_menu_activity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import apdapter.SlideShowbannerAdapter;
import me.relex.circleindicator.CircleIndicator3;
import model.SlideShowBanner;


public class FragmentTrangChu extends Fragment {

  private ImageButton imgButtonNav;
  NavigationView mNavigationView ;
  private DrawerLayout mDrawerLayout;
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

    public FragmentTrangChu() {


    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        // Inflate the layout for this fragment
mDrawerLayout = view.findViewById(R.id.drawer_layout); // drawable layout
        mNavigationView = view.findViewById(R.id.navigation_view);

       imgButtonNav = view.findViewById(R.id.btn_menu_nav); // anh xa image button
        mViewPager2 = view.findViewById(R.id.viewpager_2);// anh xa viewpager 2
        mCircleIndicator3 =view.findViewById(R.id.circle_indicator_3);
        mslidSlideShowBanners = getListSiSlideShowBanners(); // gan doi tuong list slideshow vao ham add list slideshow
        imgButtonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);


            }
        });
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

        return view;
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