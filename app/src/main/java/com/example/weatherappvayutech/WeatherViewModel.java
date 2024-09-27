package com.example.weatherappvayutech;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    private final MutableLiveData<String> city = new MutableLiveData<>();
    private final MutableLiveData<String> date = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<String> temp = new MutableLiveData<>();
    private final MutableLiveData<Integer> icon = new MutableLiveData<>();

    public void setCity(String city) {
        this.city.setValue(city);
    }
    public LiveData<String> getCity() {
        return city;
    }

    public void setDate(String date) {
        this.date.setValue(date);
    }
    public LiveData<String> getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }
    public LiveData<String> getDescription() {
        return description;
    }

    public void setTemp(String temp) {
        this.temp.setValue(temp);
    }
    public LiveData<String> getTemp() {
        return temp;
    }

    public void setIcon(int icon) {
        this.icon.setValue(icon);
    }
    public LiveData<Integer> getIcon() {
        return icon;
    }
}
