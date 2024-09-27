package com.example.weatherappvayutech;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;


public class fragment_hourly_forecast extends Fragment {

    RecyclerView recyclerView;
    ArrayList<hourDatamodel> dataholder;

    public fragment_hourly_forecast() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hourly_forecast, container, false);

        TextView cityH = view.findViewById(R.id.cityh);
        TextView conditionTextViewH = view.findViewById(R.id.conditionh);
        TextView dateTextViewH = view.findViewById(R.id.dateh);
        TextView temp_conditonTextViewH = view.findViewById(R.id.temp_conditionH);
        ImageView weather_iconH = view.findViewById(R.id.weather_resourceh);

        WeatherViewModel weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        weatherViewModel.getCity().observe(getViewLifecycleOwner(), city ->{
            city = city.toUpperCase();
            cityH.setText(city);
    });

        weatherViewModel.getDate().observe(getViewLifecycleOwner(), dateTextViewH::setText);

        weatherViewModel.getDescription().observe(getViewLifecycleOwner(), description -> {
            description = description.toUpperCase();
            conditionTextViewH.setText(description);
        });

        weatherViewModel.getIcon().observe(getViewLifecycleOwner(), iconName -> {

            if (iconName != null) {
                int drawableId = getResources().getIdentifier(String.valueOf(iconName), "drawable", requireContext().getPackageName());

                if (drawableId != 0) {
                    weather_iconH.setImageResource(drawableId);
                } else {
                    Log.e("WeatherApp", "Drawable resource not found for: " + iconName);
                    weather_iconH.setImageResource(R.drawable.ic_search_black_24dp);
                }
            }

        });

        weatherViewModel.getTemp().observe(getViewLifecycleOwner(), temp_conditonTextViewH::setText);

        recyclerView=view.findViewById(R.id.recycler_view_hourly_forecast);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        hourlyAdapter adapter = new hourlyAdapter(dataholder);
        recyclerView.setAdapter(adapter);
        dataholder=new ArrayList<>();

        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:00 a");

        String[] conditions = {"Clear", "Clouds", "Fog", "Light rain", "Rain", "Light Clouds", "Storm", "Clear", "Snow", "Clear"};
        int[] icons = {R.drawable.art_clear, R.drawable.art_clouds, R.drawable.art_fog, R.drawable.art_light_rain,
                R.drawable.art_rain, R.drawable.art_light_clouds, R.drawable.art_storm, R.drawable.art_clear,
                R.drawable.art_snow, R.drawable.art_clear};
        String[] temperatures = {"30°C", "32°C", "28°C", "29°C", "27°C", "30°C", "30°C", "30°C", "20°C", "20°C"};

        for (int i = 1; i <= 10; i++) {
            LocalTime timePlusHours = currentTime.plusHours(i);
            String formattedTime = timePlusHours.format(timeFormatter);

            hourDatamodel ob = new hourDatamodel(formattedTime, icons[i - 1], temperatures[i - 1], conditions[i - 1]);
            dataholder.add(ob);
        }
        recyclerView.setAdapter(new hourlyAdapter(dataholder));


        return view;

    }


}