package com.example.android.cars_mvvm_room.Network;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(Url.URL_DATA)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Api api = retrofit.create(Api.class);
}
