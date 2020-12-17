package com.example.viewpager2;

import android.app.Application;

import androidx.room.Room;

import com.example.viewpager2.data.entities.AppDataBase;
import com.example.viewpager2.utils.PreferenceUtils;

public class App extends Application {

    public static AppDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        // singleTone инициилизация единожды здесь
        PreferenceUtils.init(this);

        dataBase = Room.databaseBuilder(this,
                AppDataBase.class, "test-database")
                .allowMainThreadQueries()
                .build();
    }
}
