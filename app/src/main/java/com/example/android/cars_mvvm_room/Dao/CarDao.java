package com.example.android.cars_mvvm_room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android.cars_mvvm_room.Modal.Car;

import java.util.List;

@Dao
public interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Car> carList);

    @Query("SELECT * FROM car")
    LiveData<List<Car>> getCars();

    @Query("DELETE FROM car")
    void deleteAll();
}
