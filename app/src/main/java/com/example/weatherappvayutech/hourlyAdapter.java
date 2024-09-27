package com.example.weatherappvayutech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hourlyAdapter extends RecyclerView.Adapter<hourlyAdapter.myViewHolder> {

    ArrayList<hourDatamodel> Hourdataholder;
    public hourlyAdapter(ArrayList<hourDatamodel> Hourdataholder) {
        this.Hourdataholder = Hourdataholder;
    }

    @NonNull
    @Override
    public hourlyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_items,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position)
    {
        holder.img.setImageResource(Hourdataholder.get(position).getImage());
        holder.desc.setText(Hourdataholder.get(position).getDesc());
        holder.time.setText(Hourdataholder.get(position).getTime());
        holder.temp.setText(Hourdataholder.get(position).getTemp());
    }


    @Override
    public int getItemCount() {
        return Hourdataholder.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView time,temp,desc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            time=itemView.findViewById(R.id.time);
            temp=itemView.findViewById(R.id.temph);
            img=itemView.findViewById(R.id.weather_img);
            desc=itemView.findViewById(R.id.desc);
        }
    }
}
