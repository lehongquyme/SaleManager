package com.example.salemanager.fragment.recycfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salemanager.R;
import com.example.salemanager.fragment.objectfragment.ObjectHD;

import java.util.ArrayList;
import java.util.Base64;

public class RecyccleAdapterHD extends RecyclerView.Adapter<RecyccleAdapterHD.ViewHolder> {

    ArrayList<ObjectHD> mDataList;

    public RecyccleAdapterHD(ArrayList<ObjectHD> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public RecyccleAdapterHD.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemhd, parent, false);
        return new RecyccleAdapterHD.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyccleAdapterHD.ViewHolder holder, final int position) {
        ObjectHD sanpham = mDataList.get(position);
        if (sanpham != null) {
            holder.nameSp.setText("Tên Sản Phẩm: " + sanpham.getHangHD());
            holder.codeHd.setText("Mã Hoá Đơn: " + sanpham.getIdHD());
            holder.amongHd.setText("Số lượng: " + sanpham.getAmongHD());
            holder.priceHd.setText("Đơn giá: " + sanpham.getPriceHD());
            holder.TotalHd.setText("Thành Tiền: " + sanpham.getTotalHD());
            holder.dateHd.setText("Ngày Nhập: " + sanpham.getTotalHD());

        }

    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameSp, codeHd,amongHd,dateHd, priceHd,TotalHd;
        public ViewHolder(View itemView) {
            super(itemView);
            nameSp = itemView.findViewById(R.id.txthd);
            codeHd = itemView.findViewById(R.id.idHd);
            amongHd = itemView.findViewById(R.id.amongHd);
            priceHd = itemView.findViewById(R.id.priceHd);
            dateHd = itemView.findViewById(R.id.dateHd);
            TotalHd = itemView.findViewById(R.id.totalHd);


        }
    }


}

