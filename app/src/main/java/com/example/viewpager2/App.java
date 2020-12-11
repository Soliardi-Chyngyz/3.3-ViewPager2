package com.example.viewpager2;

import android.app.Application;

import com.example.viewpager2.utils.PreferenceUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // singleTone инициилизация единожды здесь
        PreferenceUtils.init(this);
    }
}
