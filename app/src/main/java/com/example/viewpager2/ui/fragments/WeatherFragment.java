package com.example.viewpager2.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewpager2.data.models.Weather;
import com.example.viewpager2.data.network.GhibliService;
import com.example.viewpager2.databinding.FragmentWeatherBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getRetrofit();
        setTime();
    }

    private void setTime() {

    }

    private void getRetrofit() {
        GhibliService.getApiWeatherInterface().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                assert response.body() != null;
                setData(response.body());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("fail", "onFailure: ", t);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setData(Weather body) {
        // temperare
        int temp = (int) ((body.getMain().getTemp() - 273));
        binding.temprerare.setText(String.valueOf(temp + "ºC"));
        // icon of main temperare
        String iconCode = body.getWeather().get(0).getIcon();
        String iconUrl = "http://openweathermap.org/img/wn/" + iconCode + "@2x.png";
        Picasso.with(getContext()).load(iconUrl).into(binding.weatherConditions);
        // humidity
        binding.txtHumidity.setText(body.getMain().getHumidity().toString() + " %");
        // barometer
        binding.txtBarometer.setText(body.getMain().getPressure().toString() + " hPa");
        //wind
        binding.txtWind.setText(body.getWind().getSpeed().toString() + " m/s");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}