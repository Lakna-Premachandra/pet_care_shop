package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerReviewsActivity extends AppCompatActivity {
    EditText EditTextName, EditTextAddress, EditTextReview;
    Button ButtonAddReview, ButtonViewReviews;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reviews);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextName=(EditText) findViewById(R.id.txt_RevCstName);
        EditTextAddress=(EditText) findViewById(R.id.txt_RevCstAddress);
        EditTextReview=(EditText) findViewById(R.id.txt_CstReview);

        ButtonAddReview=(Button) findViewById(R.id.btnAddreview);
//        ButtonViewReviews=(Button)findViewById(R.id.btnViewReview);

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
                    CstReviews cstReviews=new CstReviews(EditTextName.getText().toString(),
                            EditTextAddress.getText().toString(),
                            EditTextReview.getText().toString());
                    if (dbHelper.SaveCustomerReview(cstReviews))
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

//        ButtonViewReviews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(CustomerReviewsActivity.this,AllReviewsActivity.class));
//            }
//        });
    }
}