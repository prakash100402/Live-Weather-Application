package com.example.weatherappvayutech;

public class WeatherData {

    private double temp;
    private double humidity;
    private double windSpeed;
    private double max_temp;
    private String description;
    private String city;
    private int icon;

    public WeatherData(double temp, double humidity, double windSpeed, double max_temp, String description, String city, int icon) {
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.max_temp = max_temp;
        this.description = description;
        this.city = city;
        this.icon = icon;

    }

    public double getTemperature() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public String getDescription() {
        return description.toUpperCase();
    }

    public String getCity() {
        return city.toUpperCase();
    }

    public int getIcon() {
        if (icon>= 200 && icon <= 232) {
            return R.drawable.art_storm;
        } else if (icon >= 300 && icon <= 321) {
            return R.drawable.art_light_rain;
        } else if (icon >= 500 && icon <= 504) {
            return R.drawable.art_rain;
        } else if (icon == 511) {
            return R.drawable.art_snow;
        } else if (icon >= 520 && icon<= 531) {
            return R.drawable.art_rain;
        } else if (icon >= 600 && icon <= 622) {
            return R.drawable.art_snow;
        } else if (icon >= 701 && icon <= 761) {
            return R.drawable.art_fog;
        } else if (icon == 762 || icon== 781) {
            return R.drawable.art_storm;
        } else if (icon == 800) {
            return R.drawable.art_clear;
        } else if (icon == 801) {
            return R.drawable.art_light_clouds;
        } else if (icon >= 802 && icon <= 804) {
            return R.drawable.art_clouds;
        }
        return -1;
    }



}
