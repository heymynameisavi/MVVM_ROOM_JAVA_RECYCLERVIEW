package com.example.android.cars_mvvm_room.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android.cars_mvvm_room.Dao.CarDao;
import com.example.android.cars_mvvm_room.Database.CarDatabase;
import com.example.android.cars_mvvm_room.Modal.Car;

import java.util.List;

public class CarRepository {

    private CarDatabase carDatabase;
    private LiveData<List<Car>> getAllCars;

    public CarRepository(Application application) {
        carDatabase=CarDatabase.getInstance(application);
        getAllCars=carDatabase.carDao().getCars();
    }

    public void insert(List<Car> carList) {
        new InsertAsyncTask(carDatabase).execute(carList);
    }
    public LiveData<List<Car>> getAllCars() {
        return getAllCars;
    }

    static class InsertAsyncTask extends AsyncTask<List<Car>, Void, Void> {

        private CarDao carDao;

        InsertAsyncTask(CarDatabase carDatabase) {
            carDao = carDatabase.carDao();
        }
        @Override
        protected Void doInBackground(List<Car>... lists) {
            carDao.insert(lists[0]);
            return null;
        }
    }
}
