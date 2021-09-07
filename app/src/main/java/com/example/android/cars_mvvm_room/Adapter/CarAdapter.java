package com.example.android.cars_mvvm_room.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.cars_mvvm_room.Modal.Car;
import com.example.android.cars_mvvm_room.R;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{

    private Context context;
    private List<Car> carList;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_row, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        Car car = carList.get(position);
        holder.id.setText("Id "+car.getId());
        holder.name.setText("Name "+car.getName());
        holder.desc.setText("Desc "+car.getDesc());

        Glide.with(context)
                .load(car.getImage())
                .into(holder.image);
    }

    public void getAllCars(List<Car> carList)
    {
        this.carList=carList;
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder{

        public TextView id, name, desc;
        public ImageView image;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            image = itemView.findViewById(R.id.image);
        }
    }
}
