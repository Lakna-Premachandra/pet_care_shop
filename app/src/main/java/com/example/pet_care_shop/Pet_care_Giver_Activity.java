package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pet_care_Giver_Activity extends AppCompatActivity {
    EditText EditTextName, EditTextAddress, EditTextReview;
    Button ButtonNewWork, ButtonAddReview, ButtonViewReviews;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_care_giver);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextName=(EditText)  findViewById(R.id.txt_RevName);
        EditTextAddress=(EditText) findViewById(R.id.txt_RevAddress);
        EditTextReview=(EditText) findViewById(R.id.txt_YourReview);


        ButtonAddReview=(Button) findViewById(R.id.btn_AddReview);


//        ButtonNewWork.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Pet_care_Giver_Activity.this,PostedAdsActivity.class));
//            }
//        });

        ButtonAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EditTextName.getText().toString().isEmpty()||
                EditTextAddress.getText().toString().isEmpty()||
                EditTextReview.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields can't be empty",Toast.LENGTH_LONG).show();
                }
                else {
                    ClnReviews clnReviews=new ClnReviews(EditTextName.getText().toString(),
                            EditTextAddress.getText().toString(),
                            EditTextReview.getText().toString());
                    if (dbHelper.SaveCleanerReview(clnReviews))
                    {
                        Toast.makeText(getApplicationContext(),"Review Added Successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Failed to Add Review",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}