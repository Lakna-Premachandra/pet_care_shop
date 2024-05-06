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
import java.util.ArrayList;
import android.util.Patterns;

public class LoginActivity1 extends AppCompatActivity {
    EditText EditTextEmail1, EditTextPassword1;
    Button ButtonLogin1;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        db = new DBHelper(this);
        db.OpenDB();

        EditTextEmail1 = findViewById(R.id.txt_L1_Email);
        EditTextPassword1 = findViewById(R.id.txt_L1_Password);
        ButtonLogin1 = findViewById(R.id.btnLogin1);
        Spinner spinnerUserType = findViewById(R.id.spinnerUserType);

        String[] userTypes = {"Customer", "Caregiver"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userTypes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUserType.setAdapter(adapter);

        TextView textViewSignUp = findViewById(R.id.textView6);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to SignUp activity
                Intent signUpIntent = new Intent(LoginActivity1.this, SignUp.class);
                startActivity(signUpIntent);
            }
        });

        ButtonLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditTextEmail1.getText().toString().trim();
                String password = EditTextPassword1.getText().toString().trim();
                String selectedUserType = spinnerUserType.getSelectedItem().toString();


                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    EditTextEmail1.setError("Enter a valid email address");
//                    EditTextEmail1.requestFocus();
                    Toast.makeText(getApplicationContext(), "Enter a valid email address", Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<User> userDetails = db.ValidLogin(email, password);

                if (userDetails.size() != 0) {
                    User user = userDetails.get(0);
                    String userType = user.getUserType();
                    String userEmail = user.getEmail();

                    Intent dashboardIntent;

                    if (userType.equals(selectedUserType)) {
                        Toast.makeText(getApplicationContext(), "User found: " + userType, Toast.LENGTH_LONG).show();

                        if (userType.equals("Customer")) {
                            dashboardIntent = new Intent(LoginActivity1.this, Pet_Owner_DashboardActivity.class);
                        } else if (userType.equals("Caregiver")) {
                            dashboardIntent = new Intent(LoginActivity1.this, Caregiver_DashboardActivity.class);
                        } else {
                            return;
                        }

                        dashboardIntent.putExtra("userEmail", userEmail);
                        startActivity(dashboardIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid User Type. Try Again", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid User. Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
