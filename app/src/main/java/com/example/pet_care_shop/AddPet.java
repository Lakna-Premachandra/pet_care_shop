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

public class AddPet extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    Button btnSave, btnView,btnOpenGallery;
    EditText editTextPetName, editTextAge, editTextPetType, editTextBreed, editTextSex, editTextColor;
    Uri imageUri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        btnOpenGallery = findViewById(R.id.btnOpenGallery);

        editTextPetName = findViewById(R.id.editTextPetName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPetType = findViewById(R.id.editTextPetType);
        editTextBreed = findViewById(R.id.editTextBreed);
        editTextSex = findViewById(R.id.editTextSex);
        editTextColor = findViewById(R.id.editTextColor);
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
                startActivity(new Intent(AddPet.this, DisplayActivity.class));
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
        String petName = editTextPetName.getText().toString();
        String petType = editTextPetType.getText().toString();
        String breed = editTextBreed.getText().toString();
        String sex = editTextSex.getText().toString();
        String color = editTextColor.getText().toString();
        int age = 0;
        try {
            age = Integer.parseInt(editTextAge.getText().toString());
        } catch (NumberFormatException e) {

            Toast.makeText(this, "Invalid age format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data to SharedPreferences
        SharedPreferences share = getSharedPreferences("details", MODE_PRIVATE);
        SharedPreferences.Editor edit_obj = share.edit();

        edit_obj.putString("petName", petName);
        edit_obj.putInt("age", age);
        edit_obj.putString("petType", petType);
        edit_obj.putString("breed", breed);
        edit_obj.putString("sex", sex);
        edit_obj.putString("color", color);
        edit_obj.putString("imageUri", imageUri.toString());
        edit_obj.apply();

        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
    }
}
