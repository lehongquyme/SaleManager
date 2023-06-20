package com.example.salemanager.fragment.objectfragment;

import java.util.HashMap;
import java.util.Map;

public class ObjectSP {

      String imgSp, nameSp, maSp, cpu, ram, rom, price,tinhtrang;

    public ObjectSP() {
    }

    public ObjectSP(String imgSp, String nameSp, String maSp, String cpu, String ram, String rom, String price,String tinhtrang) {
        this.imgSp = imgSp;
        this.nameSp = nameSp;
        this.maSp = maSp;
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.price = price;
        this.tinhtrang =tinhtrang;
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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }
    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
