package com.example.pet_care_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.ViewHolder> {

    private Context context;
    private ArrayList id, name, pet_name, phone, pet_breed , pet_type , pet_age , pet_sex , times;


    public AdsAdapter(Context context, ArrayList id, ArrayList name, ArrayList pet_name, ArrayList phone, ArrayList pet_breed, ArrayList pet_type, ArrayList pet_age, ArrayList pet_sex, ArrayList times) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.pet_name = pet_name;
        this.phone = phone;
        this.pet_breed  = pet_breed;
        this.pet_type  = pet_type;
        this.pet_age = pet_age;
        this.pet_sex  = pet_sex;
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_ads,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.pet_name.setText(String.valueOf(pet_name.get(position)));
        holder.phone.setText(String.valueOf(phone.get(position)));
        holder.pet_breed.setText(String.valueOf(pet_breed .get(position)));
        holder.pet_type.setText(String.valueOf(pet_type .get(position)));
        holder.pet_age.setText(String.valueOf(pet_age.get(position)));
        holder.pet_sex.setText(String.valueOf(pet_sex.get(position)));
        holder.times.setText(String.valueOf(times.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, pet_name, phone, pet_breed, pet_type, pet_age, pet_sex, times;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.adID);
            name=itemView.findViewById(R.id.adName);
            pet_name=itemView.findViewById(R.id.adAddress);
            phone=itemView.findViewById(R.id.adPhone);
            pet_breed=itemView.findViewById(R.id.adNIC);
            pet_type=itemView.findViewById(R.id.adProperty);
            pet_age=itemView.findViewById(R.id.adBedrooms);
            pet_sex=itemView.findViewById(R.id.adBathrooms);
            times=itemView.findViewById(R.id.adTimes);
        }
    }
}
