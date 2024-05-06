package com.example.pet_care_shop;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Caregiver_DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_dashboard);


                Button ButtonNewWork = findViewById(R.id.btn_NewWork);
                Button ButtonAddReview = findViewById(R.id.btn_AddReview);
        Button ButtonViewReviews = findViewById(R.id.btn_VeiwReview);
                Button logoutButton = findViewById(R.id.logoutButton);
        Button btnAboutUs = findViewById(R.id.btn_AboutUs2);
        Button btnContactUs = findViewById(R.id.btn_ContactUs2);

        TextView userWelcomeTextView = findViewById(R.id.userwelcomeTextView2);

        String userEmail = getIntent().getStringExtra("userEmail");

        TextView userEmailTextView = findViewById(R.id.userEmailTextView2);
        userEmailTextView.setText(userEmail);

        userWelcomeTextView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Caregiver_DashboardActivity.this, CustomerInfoNewActivity.class);

                startActivity(intent);
            }
        });


        ButtonNewWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Caregiver_DashboardActivity.this,PostedAdsActivity.class));
            }
        });


        ButtonViewReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Caregiver_DashboardActivity.this,AllReviewsActivity.class));
            }
        });

        ButtonAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Caregiver_DashboardActivity.this,Pet_care_Giver_Activity.class));

            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Caregiver_DashboardActivity.this, AboutUsActivity.class));
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Caregiver_DashboardActivity.this, ContactUsActivity.class));
            }
        });

                logoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Caregiver_DashboardActivity.this,MainActivity.class));
                    }
                });
            }
        }
