package com.example.cryptoapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates {
    @SerializedName("BTC")
    @Expose
    public Float btc;
    @SerializedName("ETH")
    @Expose
    public Float eth;
    @SerializedName("LTC")
    @Expose
    public Float ltc;

    @Override
    public String toString() {
        return "Rates{" +
                "btc=" + btc +
                ", eth=" + eth +
                ", ltc=" + ltc +
                '}';
    }
}