package com.example.monoad.model;


import com.google.gson.annotations.SerializedName;

public class contact_annonce {
    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private  String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
