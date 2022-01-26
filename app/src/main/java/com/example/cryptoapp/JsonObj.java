package com.example.cryptoapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonObj {
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("terms")
    @Expose
    public String terms;
    @SerializedName("privacy")
    @Expose
    public String privacy;
    @SerializedName("timestamp")
    @Expose
    public Integer timestamp;
    @SerializedName("target")
    @Expose
    public String target;
    @SerializedName("rates")
    @Expose
    public Rates rates;

    public JsonObj withSuccess(Boolean success) {
        this.success = success;
        return this;
    }


}