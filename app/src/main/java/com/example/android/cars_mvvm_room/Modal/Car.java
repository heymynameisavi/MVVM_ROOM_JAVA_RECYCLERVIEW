package com.example.android.cars_mvvm_room.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "car", indices = @Index(value = {"id"}, unique = true))
public class Car {

    @PrimaryKey(autoGenerate = true)
    private int carId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("desc")
    @ColumnInfo(name = "desc")
    private String desc;

    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;

    public Car(int carId, int id, String name, String desc, String image) {
        this.carId = carId;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
