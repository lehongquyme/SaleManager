package com.example.salemanager.fragment.recycfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salemanager.R;
import com.example.salemanager.fragment.objectfragment.ObjectSP;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class RecycleAdapterTrangChu extends RecyclerView.Adapter<RecycleAdapterTrangChu.MyViewHolder> {
     List<ObjectSP> listsp;

    public RecycleAdapterTrangChu(List<ObjectSP> list) {
        this.listsp = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsp, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ObjectSP sanpham = listsp.get(position);
        if (sanpham == null) {
            holder.imgSanPham.setImageResource(R.drawable.aircraft );
            holder.nameSp.setText("");
            holder.codeSp.setText("");
            holder.cpu.setText("");
            holder.ram.setText("");
            holder.rom.setText("");
            holder.priceSP.setText("");
            return;
        }

        bytesToImage(holder.imgSanPham, sanpham.getImgSp());
        holder.nameSp.setText("Tên Sản Phẩm: " + sanpham.getNameSp());
        holder.codeSp.setText("Mã Sản Phẩm: " + sanpham.getMaSp());

        holder.cpu.setText("CPU: " + sanpham.getCpu());
        holder.ram.setText("Ram: " + sanpham.getRam());
        holder.rom.setText("Rom: " + sanpham.getRom());
        holder.priceSP.setText("Giá sản phẩm: " + sanpham.getPrice());
    }

    @Override
    public int getItemCount() {
        if (listsp != null) {
            return listsp.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameSp, codeSp, cpu, ram, rom, priceSP;
        ImageView imgSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSp);
            nameSp = itemView.findViewById(R.id.itemtenSp);
            codeSp = itemView.findViewById(R.id.itemMaSp);
            cpu = itemView.findViewById(R.id.itemCpu);
            ram = itemView.findViewById(R.id.itemRam);
            rom = itemView.findViewById(R.id.itemRom);
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

