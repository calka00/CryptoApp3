package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    double defaultValue = 4.08;
    boolean isInDolar = true;
    boolean isValueSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isValueSet==false){
        String connection = "http://api.coinlayer.com/";


        Retrofit retrofit = new Retrofit.Builder().baseUrl(connection).
                addConverterFactory(GsonConverterFactory.create())
                .build();

            Get get = retrofit.create(Get.class);

            Call<JsonObj> call = get.getJsonobj();

        call.enqueue(new Callback<JsonObj>() {
            @Override
            public void onResponse(Call<JsonObj> call, Response<JsonObj> response) {

                JsonObj exchangeRates =  response.body();

                String[] names = {"Bitcoin","Ethereum","Litcoin"};
                String[] rates = {Float.toString(exchangeRates.rates.btc),Float.toString(exchangeRates.rates.eth),
                        Float.toString(exchangeRates.rates.ltc)};
                Integer[] imageId ={R.drawable.btc,R.drawable.eth,R.drawable.ltc};
                Activity main = getContext();
                CustomListAdapter listAdapter = new CustomListAdapter(main, names , rates, imageId);
                ListView listView = (ListView) findViewById(R.id.listResult);
                listView.setAdapter(listAdapter);



            }

            @Override
            public void onFailure(Call<JsonObj> call, Throwable t) {

            }
        });
            isValueSet=true;
    }


        Button changeValues  = (Button) findViewById(R.id.button2);
        EditText values  = (EditText) findViewById(R.id.editTextNumber);


        changeValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isInDolar) {
                    defaultValue = Double.parseDouble(values.getText().toString());
                    TextView rates1 = (TextView) findViewById(R.id.rates1);
                    TextView rates2 = (TextView) findViewById(R.id.rates2);
                    TextView rates3 = (TextView) findViewById(R.id.rates3);
                    TextView[] arr = {rates1, rates2, rates3};
                    for (TextView rate : arr) {
                        double temp = 0;
                        temp = Double.parseDouble(rate.getText().toString());
                        temp *= defaultValue;
                        String s = String.valueOf(temp);
                        rate.setText(s);

                    }
                    changeValues.setText("Zamień na dolar");
                    isInDolar = false;
                } else {
                    defaultValue = Double.parseDouble(values.getText().toString());
                    TextView rates1 = (TextView) findViewById(R.id.rates1);
                    TextView rates2 = (TextView) findViewById(R.id.rates2);
                    TextView rates3 = (TextView) findViewById(R.id.rates3);
                    TextView[] arr = {rates1, rates2, rates3};
                    for (TextView rate : arr) {
                        double temp = 0;
                        temp = Double.parseDouble(rate.getText().toString());
                        temp /= defaultValue;
                        String s = String.valueOf(temp);
                        rate.setText(s);

                    }
                    changeValues.setText("Zamień na Pln");
                    isInDolar = true;
                }
            }
        });


    }

    private Activity getContext() {
        return this;
    }
}