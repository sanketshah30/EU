package com.example.hp.eu.model;

import android.media.Image;

public class DBHelperModel {
    String name, phonenumber, password, address;
String userType;
    String imgurl;
    public static final String STORAGE_PATH_UPLOADS = "uploads/";
    public static final String DATABASE_PATH_UPLOADS = "uploads";

    public DBHelperModel(String name, String phonenumber, String password, String address, String url, String userType) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.password = password;
        this.address = address;
        this.imgurl = url;
        this.userType=userType;
    }
    public DBHelperModel(String name, String phonenumber, String password, String address, String url) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.password = password;
        this.address = address;
        this.imgurl = url;
    }

    public DBHelperModel() {
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}


