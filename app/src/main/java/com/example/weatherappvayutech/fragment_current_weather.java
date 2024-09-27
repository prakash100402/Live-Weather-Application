package com.example.weatherappvayutech;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class fragment_current_weather extends Fragment {

    private TextView temperatureTextView, humidityTextView, windSpeedTextView, max_tempTextview;
    private TextView cityTextView, conditionTextView, dateTextView, temp_conditonTextView;
    private ImageView weather_icon;

    private String cityName;

    private WeatherViewModel weatherViewModel;

    // Factory method to create a new instance of this fragment using the provided city name.
    public static fragment_current_weather newInstance(String cityName) {
        fragment_current_weather fragment = new fragment_current_weather();
        Bundle args = new Bundle();
        args.putString("CITY_NAME", cityName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);



        // Initialize UI components
        temperatureTextView = view.findViewById(R.id.temph);
        humidityTextView = view.findViewById(R.id.humidity_value);
        windSpeedTextView = view.findViewById(R.id.wind_value);
        max_tempTextview = view.findViewById(R.id.temp_max);
        cityTextView = view.findViewById(R.id.city);
        conditionTextView = view.findViewById(R.id.condition);
        dateTextView = view.findViewById(R.id.date);
        temp_conditonTextView = view.findViewById(R.id.temp_condition);
        weather_icon = view.findViewById(R.id.weather_resource);

        // Set the current date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d", Locale.getDefault());
        String formattedDate = sdf.format(currentDate);
        dateTextView.setText(formattedDate);

        // Retrieve the city name from arguments
        if (getArguments() != null) {
            cityName = getArguments().getString("CITY_NAME", "London");
        }

        // Fetch weather data
        fetchWeatherData();

        weatherViewModel.setDate(formattedDate);


        return view;
    }

    private void fetchWeatherData() {
        if (cityName != null && !cityName.isEmpty()) {
            // Passing the city name to WeatherApiService
            WeatherApiService weatherApiService = new WeatherApiService(cityName);
            weatherApiService.getCurrentWeather(new WeatherApiService.WeatherCallback() {
                @Override
                public void onSuccess(WeatherData weatherData) {
                    // Updating UI on the main thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        if (weatherData != null) {
                            // Update UI with weather data
                            temperatureTextView.setText(getString(R.string.temp, weatherData.getTemperature()));
                            temp_conditonTextView.setText(getString(R.string.temp, weatherData.getTemperature()));
                            humidityTextView.setText(getString(R.string.humidity_value, weatherData.getHumidity()));
                            windSpeedTextView.setText(getString(R.string.wind_speed, weatherData.getWindSpeed()));
                            max_tempTextview.setText(getString(R.string.max_temp, weatherData.getMax_temp()));
                            conditionTextView.setText(getString(R.string.condition, weatherData.getDescription()));
                            cityTextView.setText(getString(R.string.city, weatherData.getCity()));
                            weather_icon.setImageResource(weatherData.getIcon());

                            weatherViewModel.setCity(cityName);
                            weatherViewModel.setDescription(getString(R.string.condition, weatherData.getDescription()));
                            weatherViewModel.setTemp(getString(R.string.temp, weatherData.getTemperature()));
                            weatherViewModel.setIcon(weatherData.getIcon());



                        } else {
                            // Handle the case where weatherData is null
                            showError("Failed to retrieve weather data.");
                        }
                    });
                }

                @Override
                public void onFailure(Throwable t) {
                    // Show error message on the main thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        if (t instanceof java.net.UnknownHostException) {
                            // No internet connection
                            showError("No internet connection. Please check your network settings.");
                        } else if (t instanceof java.net.SocketTimeoutException) {
                            // Request timed out
                            showError("Request timed out. Please try again later.");
                        } else {
                            // Generic error message for other types of exceptions
                            showError("An error occurred while fetching the weather data. Please try again.");
                        }
                    });
                }
            });
        } else {
            showError("Welcome to Weather App");
        }
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
