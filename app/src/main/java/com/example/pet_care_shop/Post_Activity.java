package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;

import android.widget.Toast;

import java.util.ArrayList;

public class Post_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> id, name, pet_name, phone, pet_breed, pet_type, pet_age, pet_sex, times;
    DBHelper db;
    AdsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        db=new DBHelper(this);

        id=new ArrayList<>();
        name=new ArrayList<>();
        pet_name=new ArrayList<>();
        phone=new ArrayList<>();
        pet_breed =new ArrayList<>();
        pet_type =new ArrayList<>();
        pet_age =new ArrayList<>();
        pet_sex =new ArrayList<>();
        times=new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        adapter=new AdsAdapter(this,id, name, pet_name, phone, pet_breed, pet_type, pet_age, pet_sex, times);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = db.getdata();
        if (cursor.getCount()==0)
        {
            Toast.makeText(Post_Activity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            while (cursor.moveToNext())
            {
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