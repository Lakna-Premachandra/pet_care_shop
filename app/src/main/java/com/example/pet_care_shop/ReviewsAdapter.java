package com.example.pet_care_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder>{
    private Context context;
    private ArrayList id, name, address, reviews;

    public ReviewsAdapter(Context context, ArrayList id, ArrayList name, ArrayList address, ArrayList reviews) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.address = address;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_reviews,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.address.setText(String.valueOf(address.get(position)));
        holder.reviews.setText(String.valueOf(reviews.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, address, reviews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.RevID);
            name=itemView.findViewById(R.id.RevName);
            address=itemView.findViewById(R.id.RevAddress);
            reviews=itemView.findViewById(R.id.UserRev);
        }
    }
}
