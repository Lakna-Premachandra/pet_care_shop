package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;

public class SignUp extends AppCompatActivity {
    EditText EditTextEmail, EditTextPassword, EditTextConPassword;
    Spinner SpinnerUserType;
    Button ButtonSignUp;

    private DBHelper dbHelper;

    String UserType[] = {"Customer", "Caregiver"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        EditTextEmail = findViewById(R.id.txt_Sign_Email);
        EditTextPassword = findViewById(R.id.txt_SignPass);
        EditTextConPassword = findViewById(R.id.txt_SignConPass);
        ButtonSignUp = findViewById(R.id.btn_SignUp);

        SpinnerUserType = findViewById(R.id.sp_UserType);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerUserType.setAdapter(ad);

        TextView textViewLogin = findViewById(R.id.textView6);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to LoginActivity
                Intent loginIntent = new Intent(SignUp.this, LoginActivity1.class);
                startActivity(loginIntent);
            }
        });

        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditTextEmail.getText().toString().trim();
                String password = EditTextPassword.getText().toString().trim();
                String confirmPassword = EditTextConPassword.getText().toString().trim();
                String userType = SpinnerUserType.getSelectedItem().toString();

                // Check if any field is empty
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                    return;
                }

                // Check if email is valid
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Enter a valid email address", Toast.LENGTH_LONG).show();
                    return;
                }

                // Check password length
                if (password.length() < 4) {
                    Toast.makeText(getApplicationContext(), "Registration Failed. Password must have at least 4 characters", Toast.LENGTH_LONG).show();
                    return;
                }

                // Check if password matches confirm password
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Registration Failed. Password and confirm password should match.", Toast.LENGTH_LONG).show();
                    return;
                }

                // Proceed with user registration
                User user = new User(email, password, userType);
                if (dbHelper.SignUpUser(user)) {
                    Toast.makeText(getApplicationContext(), "User registered Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User registration failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
