package com.example.dek_d.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String picture,tile,message;

    public Data() {}

    public Data(String picture, String tile, String message) {
        this.picture = picture;
        this.tile = tile;
        this.message = message;
    }
    //      GET     //
    public String getPicture() {
        return picture;
    }

    public String getTile() {
        return tile;
    }

    public String getMessage() {
        return message;
    }

    //      SET     //
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Data{" +
                "picture='" + picture + '\'' +
                ", tile='" + tile + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
