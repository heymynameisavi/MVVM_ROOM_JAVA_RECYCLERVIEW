package com.example.android.cars_mvvm_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.cars_mvvm_room.Adapter.CarAdapter;
import com.example.android.cars_mvvm_room.Modal.Car;
import com.example.android.cars_mvvm_room.Network.Retrofit;
import com.example.android.cars_mvvm_room.Repository.CarRepository;
import com.example.android.cars_mvvm_room.ViewModal.CarViewModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CarViewModal carViewModal;

    private RecyclerView recyclerView;
    private List<Car> carList;
    private CarRepository carRepository;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        carRepository=new CarRepository(getApplication());
        carList=new ArrayList<>();
        carAdapter=new CarAdapter(this, carList);

        carViewModal = new ViewModelProvider(this).get(CarViewModal.class);
        networkRequest();
        carViewModal.getAllCar().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> carList) {
                recyclerView.setAdapter(carAdapter);
                carAdapter.getAllCars(carList);

                Log.d("main", "onChanged: "+carList);
            }
        });
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit();
        Call<List<Car>> call = retrofit.api.getAllCars();
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful()) {
                    carRepository.insert(response.body());
                    Log.d("main", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}