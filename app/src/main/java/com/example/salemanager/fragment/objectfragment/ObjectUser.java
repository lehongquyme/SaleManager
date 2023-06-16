package com.example.salemanager.fragment.objectfragment;

public class ObjectUser {   String fullname, gmail, phone, image;


    public ObjectUser() {
    }

    public ObjectUser(String fullname, String gmail, String phone, String image) {
        this.fullname = fullname;
        this.gmail = gmail;
        this.phone = phone;
        this.image = image;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ObjectUser{" +
                "fullname='" + fullname + '\'' +
                ", gmail='" + gmail + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}