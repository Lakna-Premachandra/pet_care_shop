package com.example.pet_care_shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class CustomerInfoNewActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    Button btnSave, btnView, btnOpenGallery;
    EditText editTextName, editTextAge, editTextPhoneNumber, editTextAddress, editTextEmail, editTextGender;
    Uri imageUri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info_new);

        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        btnOpenGallery = findViewById(R.id.btnOpenGallery);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextGender = findViewById(R.id.editTextGender);
        imageView = findViewById(R.id.imageView);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerInfoNewActivity.this,DisplayCustomerActivity.class));
            }
        });

        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            // Load image into ImageView
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private void saveData() {
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String address = editTextAddress.getText().toString();
        String email = editTextEmail.getText().toString();
        String gender = editTextGender.getText().toString();

        if (imageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("customer_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("age", age);
        editor.putString("phoneNumber", phoneNumber);
        editor.putString("address", address);
        editor.putString("email", email);
        editor.putString("gender", gender);
        editor.putString("imageUri", imageUri.toString());
        editor.apply();

        Toast.makeText(this, "Customer information saved successfully.", Toast.LENGTH_SHORT).show();
    }
}
