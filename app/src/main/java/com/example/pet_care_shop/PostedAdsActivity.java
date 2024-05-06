package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PostedAdsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> id, name, pet_name, phone, pet_breed, pet_type, pet_age, pet_sex, times;
    DBHelper db;
    AdsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_ads);

        db = new DBHelper(this);

        id = new ArrayList<>();
        name = new ArrayList<>();
        pet_name = new ArrayList<>();
        phone = new ArrayList<>();
        pet_breed = new ArrayList<>();
        pet_type = new ArrayList<>();
        pet_age = new ArrayList<>();
        pet_sex = new ArrayList<>();
        times = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new AdsAdapter(this, id, name, pet_name, phone, pet_breed, pet_type, pet_age, pet_sex, times);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();


        Button confirmJobButton = findViewById(R.id.confirmJobButton);
        confirmJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Job Confirmed Successfully");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void displaydata() {
        Cursor cursor = db.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(PostedAdsActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                pet_name.add(cursor.getString(2));
                phone.add(cursor.getString(3));
                pet_breed.add(cursor.getString(4));
                pet_type.add(cursor.getString(5));
                pet_age.add(cursor.getString(6));
                pet_sex.add(cursor.getString(7));
                times.add(cursor.getString(8));
            }
        }
    }
}