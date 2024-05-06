package com.example.pet_care_shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Assuming you have ImageButton references
        ImageButton mapsButton = findViewById(R.id.maps_button);
        ImageButton messageButton = findViewById(R.id.message_button);
        ImageButton instagramButton = findViewById(R.id.instagram_button);
        ImageButton whatsappButton = findViewById(R.id.whatsapp_button);
        ImageButton facebookButton = findViewById(R.id.facebook_button);

        // Map Button Click
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        // Message Button Click
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        // Instagram Button Click
        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/bestcarepetshop.lk/?hl=en");
            }
        });

        // WhatsApp Button Click
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://web.whatsapp.com/");
            }
        });

        // Facebook Button Click
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.facebook.com/groups/PetCareSriLanka/");
            }
        });
    }

    private void openMap() {
        String locationLink = "https://www.google.com/maps/place/Best+Care+Pet's+Shop+-+Kollupitiya/@6.9077617,79.8331685,15z/data=!4m10!1m2!2m1!1spet+care+shop!3m6!1s0x3ae25b63288d1f17:0x30d8c828b438bf77!8m2!3d6.9077617!4d79.850678!15sCg1wZXQgY2FyZSBzaG9wWg8iDXBldCBjYXJlIHNob3CSARBwZXRfc3VwcGx5X3N0b3JlmgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVU5YZW1aZmVtaEJSUkFC4AEA!16s%2Fg%2F11lr6l0rnp?entry=ttu";
        Uri mapUri = Uri.parse(locationLink);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        startActivity(mapIntent);
    }

//    private void sendSMS() {
//        String phoneNumber = "0771234567";
//        String message = "This is a test SMS message";
//        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
//        smsIntent.setData(Uri.parse("smsto:" + phoneNumber));
//        smsIntent.putExtra("sms_body", message);
//        startActivity(smsIntent);
//    }

    private void sendEmail() {
        String recipientEmail = "recipient@example.com";
        String subject = "";
        String body = "";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send email"));
        } else {
            Toast.makeText(getApplicationContext(), "No email app installed", Toast.LENGTH_SHORT).show();
        }
    }


    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
