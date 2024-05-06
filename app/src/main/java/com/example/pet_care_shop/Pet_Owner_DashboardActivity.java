package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

public class Pet_Owner_DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner_dashboard);

        Button addPostButton = findViewById(R.id.addPostButton);
        Button ButtonViewAd = findViewById(R.id.btnViewAd);
        Button ButtonAddReview = findViewById(R.id.btnAddReviews);
        Button ButtonViewReview = findViewById(R.id.btnViewReviews);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button btnAboutUs = findViewById(R.id.btn_AboutUs);
        Button btnContactUs = findViewById(R.id.btn_ContactUs);
        Button btnAddPet = findViewById(R.id.btnAddPet);
        TextView userWelcomeTextView = findViewById(R.id.userwelcomeTextView);

        String userEmail = getIntent().getStringExtra("userEmail");

        TextView userEmailTextView = findViewById(R.id.userEmailTextView);
        userEmailTextView.setText(userEmail);

        userWelcomeTextView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Pet_Owner_DashboardActivity.this, CustomerInfoNewActivity.class);

                startActivity(intent);
            }
        });


        btnAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this, AddPet.class));
            }
        });


        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this,CustomerActivity2.class));
            }
        });


        ButtonViewAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this,Post_Activity.class));
            }
        });

        ButtonAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this,CustomerReviewsActivity.class));

            }
        });
        ButtonViewReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this,AllReviewsActivity.class));

            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this, AboutUsActivity.class));
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this, ContactUsActivity.class));
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pet_Owner_DashboardActivity.this,MainActivity.class));
            }
        });
    }
}
