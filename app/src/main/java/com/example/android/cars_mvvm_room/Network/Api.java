package com.example.android.cars_mvvm_room.Network;

import com.example.android.cars_mvvm_room.Modal.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("cars_list.json")
    Call<List<Car>> getAllCars();
}
