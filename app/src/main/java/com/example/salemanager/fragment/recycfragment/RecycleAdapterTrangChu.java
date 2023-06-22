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
            holder.nameSp.setText("Tên Sản Phẩm: " + sanpham.getNameSp());
            holder.codeSp.setText("Mã Sản Phẩm: " + sanpham.getMaSp());
            holder.cpu.setText("CPU: " + sanpham.getCpu());
            holder.ram.setText("Ram: " + sanpham.getRam());
            holder.rom.setText("Rom: " + sanpham.getRom());
            holder.tinhtrang.setText("Tình Trạng: " + sanpham.getTinhtrang());
            holder.priceSP.setText("Giá sản phẩm: " + sanpham.getPrice());
            holder.trangthai.setText(sanpham.getTrangthai());
        }
            bytesToImage(holder.imgSanPham, "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");

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
