package com.example.salemanager.fragment.objectfragment;

import java.util.HashMap;
import java.util.Map;

public class ObjectSP {
private  int cpuSanPham,ramSanPham,romSanPham,giaTienSanPham;
    private String image, tenSanPham, maSanPham,tinhtrang,trangthai;


    public ObjectSP() {
    }

    public ObjectSP(int cpuSanPham, int ramSanPham, int romSanPham, int giaTienSanPham, String image, String tenSanPham, String maSanPham, String tinhtrang, String trangthai) {
        this.cpuSanPham = cpuSanPham;
        this.ramSanPham = ramSanPham;
        this.romSanPham = romSanPham;
        this.giaTienSanPham = giaTienSanPham;
        this.image = image;
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.tinhtrang = tinhtrang;
        this.trangthai = trangthai;
    }

    public int getCpuSanPham() {
        return cpuSanPham;
    }

    public void setCpuSanPham(int cpuSanPham) {
        this.cpuSanPham = cpuSanPham;
    }

    public int getRamSanPham() {
        return ramSanPham;
    }

    public void setRamSanPham(int ramSanPham) {
        this.ramSanPham = ramSanPham;
    }

    public int getRomSanPham() {
        return romSanPham;
    }

    public void setRomSanPham(int romSanPham) {
        this.romSanPham = romSanPham;
    }

    public int getGiaTienSanPham() {
        return giaTienSanPham;
    }

    public void setGiaTienSanPham(int giaTienSanPham) {
        this.giaTienSanPham = giaTienSanPham;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
