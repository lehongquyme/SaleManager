package com.example.salemanager.fragment.objectfragment;

public class ObjectHD {
    String idHD, dateHD,hangHD,totalHD;
int priceHD,amongHD;
    public ObjectHD() {
    }

    public ObjectHD(String idHD, String dateHD, int amongHD, String hangHD, int priceHD, String totalHD) {
        this.idHD = idHD;
        this.dateHD = dateHD;
        this.amongHD = amongHD;
        this.hangHD = hangHD;
        this.priceHD = priceHD;
        this.totalHD = totalHD;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getDateHD() {
        return dateHD;
    }

    public void setDateHD(String dateHD) {
        this.dateHD = dateHD;
    }



    public String getHangHD() {
        return hangHD;
    }

    public void setHangHD(String hangHD) {
        this.hangHD = hangHD;
    }

    public int getPriceHD() {
        return priceHD;
    }

    public void setPriceHD(int priceHD) {
        this.priceHD = priceHD;
    }

    public int getAmongHD() {
        return amongHD;
    }

    public void setAmongHD(int amongHD) {
        this.amongHD = amongHD;
    }

    public String getTotalHD() {
        return totalHD;
    }

    public void setTotalHD(String totalHD) {
        this.totalHD = totalHD;
    }
}
