package com.ezzy.ishopp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Vendor implements Parcelable {
    private String business_name;
    private String business_description;
    private String business_category;
    private String business_location;
    private String mpesa_account_name;
    private String mpesa_paybill_number;
    private String user_id;

    public Vendor(String business_name, String business_description, String business_category,
                  String business_location, String mpesa_account_name, String mpesa_paybill_number,
                  String user_id) {
        this.business_name = business_name;
        this.business_description = business_description;
        this.business_category = business_category;
        this.business_location = business_location;
        this.mpesa_account_name = mpesa_account_name;
        this.mpesa_paybill_number = mpesa_paybill_number;
        this.user_id = user_id;
    }

    public Vendor() {
    }

    protected Vendor(Parcel in) {
        business_name = in.readString();
        business_description = in.readString();
        business_category = in.readString();
        business_location = in.readString();
        mpesa_account_name = in.readString();
        mpesa_paybill_number = in.readString();
        user_id = in.readString();
    }

    public static final Creator<Vendor> CREATOR = new Creator<Vendor>() {
        @Override
        public Vendor createFromParcel(Parcel in) {
            return new Vendor(in);
        }

        @Override
        public Vendor[] newArray(int size) {
            return new Vendor[size];
        }
    };

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_description() {
        return business_description;
    }

    public void setBusiness_description(String business_description) {
        this.business_description = business_description;
    }

    public String getBusiness_category() {
        return business_category;
    }

    public void setBusiness_category(String business_category) {
        this.business_category = business_category;
    }

    public String getBusiness_location() {
        return business_location;
    }

    public void setBusiness_location(String business_location) {
        this.business_location = business_location;
    }

    public String getMpesa_account_name() {
        return mpesa_account_name;
    }

    public void setMpesa_account_name(String mpesa_account_name) {
        this.mpesa_account_name = mpesa_account_name;
    }

    public String getMpesa_paybill_number() {
        return mpesa_paybill_number;
    }

    public void setMpesa_paybill_number(String mpesa_paybill_number) {
        this.mpesa_paybill_number = mpesa_paybill_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(business_name);
        dest.writeString(business_description);
        dest.writeString(business_category);
        dest.writeString(business_location);
        dest.writeString(mpesa_account_name);
        dest.writeString(mpesa_paybill_number);
        dest.writeString(user_id);
    }
}
