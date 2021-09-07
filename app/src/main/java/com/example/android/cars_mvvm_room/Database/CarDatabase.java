package com.example.android.cars_mvvm_room.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android.cars_mvvm_room.Dao.CarDao;
import com.example.android.cars_mvvm_room.Modal.Car;

@Database(entities = {Car.class}, version = 2)
public abstract class CarDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "CarDatabase";

    public abstract CarDao carDao();

    private static volatile CarDatabase INSTANCE;

    public static CarDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CarDatabase.class) {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context, CarDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };

    static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private CarDao carDao;
        PopulateAsyncTask(CarDatabase carDatabase)
        {
            carDao= carDatabase.carDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            carDao.deleteAll();
            return null;
        }
    }
}
