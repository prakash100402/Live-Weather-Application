package com.example.weatherappvayutech;

import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherApiService {


    public static String cityName;

    public WeatherApiService(String cityName) {
        WeatherApiService.cityName = cityName;
    }



    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY = "&appid=b34f56f76f8bb892d1161ad266066793";

    public interface WeatherCallback {
        void onSuccess(WeatherData weatherData);
        void onFailure(Throwable t);
    }

    public void getCurrentWeather(final WeatherCallback callback) {

        String url = BASE_URL + cityName + API_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        JSONObject jsonResponse = new JSONObject(response.body().string());
                        double temp = jsonResponse.getJSONObject("main").getDouble("temp");
                        double humidity = jsonResponse.getJSONObject("main").getDouble("humidity");
                        double windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed");
                        double max_temp = jsonResponse.getJSONObject("main").getDouble("temp_max");
                        String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
                        String name = jsonResponse.getString("name");
                        int icon = Integer.parseInt(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("id"));


                        WeatherData data = new WeatherData(temp, humidity, windSpeed, max_temp, description, name, icon);
                        callback.onSuccess(data);
                    } catch (JSONException e) {
                        callback.onFailure(e);
                    }
                } else {
                    callback.onFailure(new Exception("API error"));
                }
            }
        });
    }
}
