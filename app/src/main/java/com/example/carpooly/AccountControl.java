package com.example.carpooly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

public class AccountControl extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String username = "Sean Johnston";
        String email = "sJohnston@gmail.com";
        String phone = "(805) 867-5309";

        //set the image
        ImageView picture = findViewById(R.id.imageView2);
        picture.setImageResource(R.drawable.blank);

        //set the username
        TextView userName = findViewById(R.id.textView3);
        userName.setText(username);

        //set the rating
        RatingBar userRating = findViewById(R.id.ratingBar);
        userRating.setRating(3.7f);

        //set the email
        TextView userEmail = findViewById(R.id.textView6);
        userEmail.setText(email);

        //set the phone
        TextView userPhone = findViewById(R.id.textView9);
        userPhone.setText(phone);

        List<String> privacyArray = new ArrayList<>();
        privacyArray.add("Private");
        privacyArray.add("Public");
        Spinner privacyOptions = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.privacy_view, privacyArray);
        privacyOptions.setAdapter(adapter);
    }

}
