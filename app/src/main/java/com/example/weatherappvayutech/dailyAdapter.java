package com.example.weatherappvayutech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dailyAdapter extends RecyclerView.Adapter<dailyAdapter.dailyViewHolder>{

    ArrayList<dailyDatamodel> dailydataholder;
    public dailyAdapter(ArrayList<dailyDatamodel> dailydataholder) {
        this.dailydataholder = dailydataholder;
    }


    @NonNull
    @Override
    public dailyAdapter.dailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_items,parent,false);
        return new dailyAdapter.dailyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull dailyAdapter.dailyViewHolder holder, int position)
    {
        holder.img.setImageResource(dailydataholder.get(position).getImage());
        holder.desc.setText(dailydataholder.get(position).getDesc());
        holder.temp.setText(dailydataholder.get(position).getTemp());
        holder.date.setText(dailydataholder.get(position).getDate());
        holder.day.setText(dailydataholder.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return dailydataholder.size();
    }

    public class dailyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView date,day,temp,desc;

        public dailyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.Date);
            day=itemView.findViewById(R.id.day);
            temp=itemView.findViewById(R.id.temph);
            img=itemView.findViewById(R.id.weather_img);
            desc=itemView.findViewById(R.id.desc);

        }
    }
}
