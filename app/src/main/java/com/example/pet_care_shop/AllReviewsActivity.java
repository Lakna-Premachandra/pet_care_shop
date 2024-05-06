package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AllReviewsActivity extends AppCompatActivity {
    RecyclerView recyclerView_Reviews;
    ArrayList<String> id, name, address, reviews;
    DBHelper db;
    ReviewsAdapter reviewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        db=new DBHelper(this);

        id=new ArrayList<>();
        name=new ArrayList<>();
        address=new ArrayList<>();
        reviews=new ArrayList<>();

        recyclerView_Reviews = findViewById(R.id.recyclerView_Reviews);

        reviewsAdapter=new ReviewsAdapter(this,id, name, address, reviews);
        recyclerView_Reviews.setAdapter(reviewsAdapter);
        recyclerView_Reviews.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = db.getReviewsData();
        if (cursor.getCount()==0)
        {
            Toast.makeText(AllReviewsActivity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            while (cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(2));
                reviews.add(cursor.getString(3));

            }
        }
    }
}