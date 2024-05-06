package com.example.pet_care_shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;



import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView textViewPetName, textViewAge, textViewPetType, textViewBreed, textViewSex, textViewColor;
    ImageView imageView;

    Button buttonEdit,buttonDelete,buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        textViewPetName = findViewById(R.id.textViewPetName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewPetType = findViewById(R.id.textViewPetType);
        textViewBreed = findViewById(R.id.textViewBreed);
        textViewSex = findViewById(R.id.textViewSex);
        textViewColor = findViewById(R.id.textViewColor);
        imageView = findViewById(R.id.imageView);
        buttonEdit = findViewById(R.id.btnEdit);
        buttonDelete = findViewById(R.id.btnDelete);
        buttonHome = findViewById(R.id.btnHome);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this,Pet_Owner_DashboardActivity.class);
                startActivity(intent);
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPetDetails(v); // Pass the View object (v) as an argument
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePet(v); // Pass the View object (v) as an argument
            }
        });


        SharedPreferences share = getSharedPreferences("details", MODE_PRIVATE);
        String petName = share.getString("petName", "No Name");
        int age = share.getInt("age", 0);
        String petType = share.getString("petType", "Unknown");
        String breed = share.getString("breed", "Unknown");
        String sex = share.getString("sex", "Unknown");
        String color = share.getString("color", "Unknown");
        String imageUriString = share.getString("imageUri", null);


        textViewPetName.setText("Pet Name: " + petName);
        textViewAge.setText("Age: " + age);
        textViewPetType.setText("Pet Type: " + petType);
        textViewBreed.setText("Breed: " + breed);
        textViewSex.setText("Sex: " + sex);
        textViewColor.setText("Color: " + color);

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
        }
    }

    public void editPetDetails(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("details", MODE_PRIVATE);
        String petName = sharedPreferences.getString("petName", "");
        int age = sharedPreferences.getInt("age", 0);
        String petType = sharedPreferences.getString("petType", "");
        String breed = sharedPreferences.getString("breed", "");
        String sex = sharedPreferences.getString("sex", "");
        String color = sharedPreferences.getString("color", "");
        String imageUriString = sharedPreferences.getString("imageUri", null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Pet Details");


        final EditText editTextName = new EditText(this);
        editTextName.setText(petName);
        final EditText editTextAge = new EditText(this);
        editTextAge.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextAge.setText(String.valueOf(age)); // Pre-fill with existing age
        final EditText editTextType = new EditText(this);
        editTextType.setText(petType);
        final EditText editTextBreed = new EditText(this);
        editTextBreed.setText(breed);
        final EditText editTextSex = new EditText(this);
        editTextSex.setText(sex);
        final EditText editTextColor = new EditText(this);
        editTextColor.setText(color);


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editTextName);
        layout.addView(editTextAge);
        layout.addView(editTextType);
        layout.addView(editTextBreed);
        layout.addView(editTextSex);
        layout.addView(editTextColor);

        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String newName = editTextName.getText().toString();
                int newAge = Integer.parseInt(editTextAge.getText().toString());
                String newType = editTextType.getText().toString();
                String newBreed = editTextBreed.getText().toString();
                String newSex = editTextSex.getText().toString();
                String newColor = editTextColor.getText().toString();


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("petName", newName);
                editor.putInt("age", newAge);
                editor.putString("petType", newType);
                editor.putString("breed", newBreed);
                editor.putString("sex", newSex);
                editor.putString("color", newColor);
//                editor.putString("imageUri", newImage);

                editor.apply();

                textViewPetName.setText("Pet Name: " + newName);
                textViewAge.setText("Age: " + newAge);
                textViewPetType.setText("Pet Type: " + newType);
                textViewBreed.setText("Breed: " + newBreed);
                textViewSex.setText("Sex: " + newSex);
                textViewColor.setText("Color: " + newColor);

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

    public void deletePet(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        imageView.setImageDrawable(null);

        textViewPetName.setText("Pet Name: ");
        textViewAge.setText("Age: ");
        textViewPetType.setText("Pet Type: ");
        textViewBreed.setText("Breed: ");
        textViewSex.setText("Sex: ");
        textViewColor.setText("Color: ");

    }

}
