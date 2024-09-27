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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class fragment_daily_forecast extends Fragment {

    RecyclerView recyclerView;
    ArrayList<dailyDatamodel> dailydataholder;

    public fragment_daily_forecast() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast, container, false);

        TextView cityD = view.findViewById(R.id.cityD);
        TextView conditionTextViewD = view.findViewById(R.id.conditionD);
        TextView dateTextViewD = view.findViewById(R.id.dateD);
        TextView temp_conditonTextViewD = view.findViewById(R.id.temp_conditionD);
        ImageView weather_iconD = view.findViewById(R.id.weather_resourceD);

        WeatherViewModel weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        weatherViewModel.getCity().observe(getViewLifecycleOwner(), city ->{
            city = city.toUpperCase();
            cityD.setText(city);
        });

        weatherViewModel.getDate().observe(getViewLifecycleOwner(), dateTextViewD::setText);

        weatherViewModel.getDescription().observe(getViewLifecycleOwner(), description -> {
            description = description.toUpperCase();
            conditionTextViewD.setText(description);
        });

        weatherViewModel.getIcon().observe(getViewLifecycleOwner(), iconName -> {

            if (iconName != null) {
                int drawableId = getResources().getIdentifier(String.valueOf(iconName), "drawable", requireContext().getPackageName());

                if (drawableId != 0) {
                    weather_iconD.setImageResource(drawableId);
                } else {
                    Log.e("WeatherApp", "Drawable resource not found for: " + iconName);
                    weather_iconD.setImageResource(R.drawable.art_clear);
                }
            }

        });

        weatherViewModel.getTemp().observe(getViewLifecycleOwner(), temp_conditonTextViewD::setText);


        recyclerView=view.findViewById(R.id.recyclerViewD);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailydataholder=new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d", Locale.getDefault());
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault());


        String[] conditions = {"Clear", "Clouds", "Fog", "Light rain", "Rain", "Light Clouds", "Storm", "Clear", "Snow", "Clear"};
        int[] icons = {R.drawable.art_clear, R.drawable.art_clouds, R.drawable.art_fog, R.drawable.art_light_rain,
                R.drawable.art_rain, R.drawable.art_light_clouds, R.drawable.art_storm, R.drawable.art_clear,
                R.drawable.art_snow, R.drawable.art_clear};
        String[] temperatures = {"30°C", "32°C", "28°C", "29°C", "27°C", "30°C", "30°C", "30°C", "20°C", "20°C"};


        for (int i = 1; i <= 10; i++) {
            LocalDate nextDate = currentDate.plusDays(i);
            String formattedDate = nextDate.format(dateFormatter);
            String formattedDay = nextDate.format(dayFormatter);

            dailyDatamodel ob = new dailyDatamodel(icons[i - 1], formattedDate, formattedDay, temperatures[i - 1], conditions[i - 1]);
            dailydataholder.add(ob);
        }
        recyclerView.setAdapter(new dailyAdapter(dailydataholder));


        return view;

    }

}