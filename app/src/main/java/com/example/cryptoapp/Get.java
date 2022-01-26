package com.example.cryptoapp;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Get {
        @GET("live?access_key=d272e546ecd545ba0f89dd106a31e7a2&symbols=Btc,eth,ltc")
        Call<JsonObj> getJsonobj();
}
