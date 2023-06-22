package com.example.salemanager.fragment.objectfragment;

import java.util.HashMap;
import java.util.Map;

public class ObjectSP {
private  int cpu,ram,rom,price;
    private String imgSp, nameSp, maSp,tinhtrang,trangthai;


    public ObjectSP() {
    }

    public ObjectSP(int cpu, int ram, int rom, int price, String imgSp, String nameSp, String maSp, String tinhtrang, String trangthai) {
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.price = price;
        this.imgSp = imgSp;
        this.nameSp = nameSp;
        this.maSp = maSp;
        this.tinhtrang = tinhtrang;
        this.trangthai = trangthai;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgSp() {
        return imgSp;
    }

    public void setImgSp(String imgSp) {
        this.imgSp = imgSp;
    }

    public String getNameSp() {
        return nameSp;
    }

    public void setNameSp(String nameSp) {
        this.nameSp = nameSp;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
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
