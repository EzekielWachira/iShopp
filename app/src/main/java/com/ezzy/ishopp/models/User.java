package com.ezzy.ishopp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable {
    private String name;
    private String location;
    private String email;
    private String phone;
    private String image_url;

    public User(String name, String location, String email, String phone, String image_url) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.image_url = image_url;
    }

    public User() {
    }

    protected User(Parcel in) {
        name = in.readString();
        location = in.readString();
        email = in.readString();
        phone = in.readString();
        image_url = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(image_url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
