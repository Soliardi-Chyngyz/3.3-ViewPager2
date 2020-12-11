package com.example.viewpager2.data.models.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GhibliService {

    private GhibliService() {
    }

    private static ApiInterface apiInterface;

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
}
