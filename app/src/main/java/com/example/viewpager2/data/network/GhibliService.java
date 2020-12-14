package com.example.viewpager2.data.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GhibliService {

    private GhibliService() {
    }

    private static ApiInterface apiInterface, apiWeatherInterface;

    public static ApiInterface getApiWeatherInterface(){
        if(apiWeatherInterface == null) {
            apiWeatherInterface = buildWeatherRetrofit();
        }
        return apiWeatherInterface;
    }

    public static ApiInterface getApiInterface(){
        if (apiInterface == null) {
            apiInterface = buildRetrofit();
        }
        return apiInterface;
    }

    private static ApiInterface buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    private static ApiInterface buildWeatherRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }
}
