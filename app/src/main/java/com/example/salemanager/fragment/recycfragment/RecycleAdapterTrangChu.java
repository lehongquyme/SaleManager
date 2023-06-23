package com.example.salemanager.fragment.recycfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salemanager.R;
import com.example.salemanager.fragment.objectfragment.ObjectSP;

import java.util.ArrayList;
import java.util.Base64;


public class RecycleAdapterTrangChu extends RecyclerView.Adapter<RecycleAdapterTrangChu.ViewHolder> {

    ArrayList<ObjectSP> mDataList;

    public RecycleAdapterTrangChu(ArrayList<ObjectSP> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ObjectSP sanpham = mDataList.get(position);
        if (sanpham != null) {
            holder.nameSp.setText("Tên Sản Phẩm: " + sanpham.getTenSanPham());
            holder.codeSp.setText("Mã Sản Phẩm: " + sanpham.getMaSanPham());
            holder.cpu.setText("CPU: " + sanpham.getCpuSanPham());
            holder.ram.setText("Ram: " + sanpham.getRamSanPham());
            holder.rom.setText("Rom: " + sanpham.getRomSanPham());
            holder.tinhtrang.setText("Tình Trạng: " + sanpham.getTinhtrang());
            holder.priceSP.setText("Giá sản phẩm: " + sanpham.getGiaTienSanPham());
            holder.trangthai.setText(sanpham.getTrangthai());
        }
            bytesToImage(holder.imgSanPham, sanpham.getImage());

    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameSp, codeSp, cpu, ram, rom, tinhtrang,trangthai, priceSP;
        ImageView imgSanPham;

        public ViewHolder(View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSp);
            nameSp = itemView.findViewById(R.id.itemtenSp);
            codeSp = itemView.findViewById(R.id.itemMaSp);
            cpu = itemView.findViewById(R.id.itemCpu);
            ram = itemView.findViewById(R.id.itemRam);
            rom = itemView.findViewById(R.id.itemRom);
            tinhtrang = itemView.findViewById(R.id.itemTinhTrangSp);
            trangthai = itemView.findViewById(R.id.itemttSp);
            priceSP = itemView.findViewById(R.id.itemGiaSp);

        }
    }

    private void bytesToImage(ImageView imageView, String base64String) {
        if (!base64String.isEmpty()) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(base64String);
            }
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            Glide.with(imageView.getContext()).load(decodedByte).into(imageView);
        }
    }
}
