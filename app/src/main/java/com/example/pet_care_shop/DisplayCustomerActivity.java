package com.example.pet_care_shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayCustomerActivity extends AppCompatActivity {

    TextView textViewName, textViewAge, textViewPhone, textViewAddress, textViewEmail, textViewGender;
    ImageView imageView;

    Button buttonEdit, buttonDelete, buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_customer);

        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewPhone = findViewById(R.id.textViewPhoneNumber);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewGender = findViewById(R.id.textViewGender);
        imageView = findViewById(R.id.imageViewCustomer);
        buttonEdit = findViewById(R.id.btnEdit);
        buttonDelete = findViewById(R.id.btnDelete);
        buttonHome = findViewById(R.id.btnHome);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayCustomerActivity.this, Pet_Owner_DashboardActivity.class);
                startActivity(intent);
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCustomerDetails(v);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCustomer(v);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("customer_details", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "No Name");
        String ageString = sharedPreferences.getString("age", "0"); // Retrieve age as a string
        int age = 0;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Handle the case where ageString cannot be parsed
        }
        String phone = sharedPreferences.getString("phoneNumber", "No Phone");
        String address = sharedPreferences.getString("address", "No Address");
        String email = sharedPreferences.getString("email", "No Email");
        String gender = sharedPreferences.getString("gender", "Unknown");
        String imageUriString = sharedPreferences.getString("imageUri", null);

        textViewName.setText("Name: " + name);
        textViewAge.setText("Age: " + age);
        textViewPhone.setText("Phone: " + phone);
        textViewAddress.setText("Address: " + address);
        textViewEmail.setText("Email: " + email);
        textViewGender.setText("Gender: " + gender);

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
        }
    }

    public void editCustomerDetails(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("customer_details", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String ageString = sharedPreferences.getString("age", "0");
        int age = 0;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String phone = sharedPreferences.getString("phoneNumber", "");

        String address = sharedPreferences.getString("address", "");
        String email = sharedPreferences.getString("email", "");
        String gender = sharedPreferences.getString("gender", "");
        String imageUriString = sharedPreferences.getString("imageUri", null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Customer Details");

        final EditText editTextName = new EditText(this);
        editTextName.setText(name);
        final EditText editTextAge = new EditText(this);
        editTextAge.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextAge.setText(String.valueOf(age)); // Pre-fill with existing age
        final EditText editTextPhone = new EditText(this);
        editTextPhone.setText(phone);
        final EditText editTextAddress = new EditText(this);
        editTextAddress.setText(address);
        final EditText editTextEmail = new EditText(this);
        editTextEmail.setText(email);
        final EditText editTextGender = new EditText(this);
        editTextGender.setText(gender);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editTextName);
        layout.addView(editTextAge);
        layout.addView(editTextPhone);
        layout.addView(editTextAddress);
        layout.addView(editTextEmail);
        layout.addView(editTextGender);

        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = editTextName.getText().toString();
                int newAge = Integer.parseInt(editTextAge.getText().toString());
                String newPhone = editTextPhone.getText().toString();
                String newAddress = editTextAddress.getText().toString();
                String newEmail = editTextEmail.getText().toString();
                String newGender = editTextGender.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", newName);
                editor.putInt("age", newAge);
                editor.putString("phone", newPhone);
                editor.putString("address", newAddress);
                editor.putString("email", newEmail);
                editor.putString("gender", newGender);
                editor.apply();

                // Update TextViews to display the modified details
                textViewName.setText("Name: " + newName);
                textViewAge.setText("Age: " + newAge);
                textViewPhone.setText("Phone: " + newPhone);
                textViewAddress.setText("Address: " + newAddress);
                textViewEmail.setText("Email: " + newEmail);
                textViewGender.setText("Gender: " + newGender);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void deleteCustomer(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("customer_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        imageView.setImageDrawable(null);

        textViewName.setText("Name: ");
        textViewAge.setText("Age: ");
        textViewPhone.setText("Phone: ");
        textViewAddress.setText("Address: ");
        textViewEmail.setText("Email: ");
        textViewGender.setText("Gender: ");

    }
}
