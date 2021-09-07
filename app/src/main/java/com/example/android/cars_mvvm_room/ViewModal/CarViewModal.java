package com.example.android.cars_mvvm_room.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.cars_mvvm_room.Modal.Car;
import com.example.android.cars_mvvm_room.Repository.CarRepository;

import java.util.List;

public class CarViewModal extends AndroidViewModel {

    private CarRepository carRepository;
    private LiveData<List<Car>> getAllCars;

    public CarViewModal(@NonNull Application application) {
        super(application);
        carRepository = new CarRepository(application);
        getAllCars=carRepository.getAllCars();
    }

    public void insert(List<Car> list)
    {
        carRepository.insert(list);
    }

    public LiveData<List<Car>> getAllCar()
    {
        return getAllCars;
    }
}
