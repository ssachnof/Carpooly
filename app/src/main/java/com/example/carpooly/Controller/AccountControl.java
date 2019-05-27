package com.example.carpooly.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.carpooly.Model.RegistrationModel;
import com.example.carpooly.R;
import com.example.carpooly.viewUpdater;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountControl extends AppCompatActivity {

    private RegistrationModel model;
    //todo: you need to make sure stuff goes into the correct class here!!!!!!
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
//        String username = "Sean Johnston";
//        String email = "sJohnston@gmail.com";
//        String phone = "(805) 867-5309";
//
//        //set the image
        ImageView picture = findViewById(R.id.ProfilePicture);
        picture.setImageResource(R.drawable.carpooly3);
//
//        //set the username
//        TextView userName = findViewById(R.id.Name);
//        userName.setText(username);
//
//        //set the rating
//        RatingBar userRating = findViewById(R.id.ratingBar);
//        userRating.setRating(3.7f);
//
//        //set the email
//        TextView userEmail = findViewById(R.id.Email);
//        userEmail.setText(email);
//
//        //set the phone
//        TextView userPhone = findViewById(R.id.PhoneNumber);
//        userPhone.setText(phone);

        List<String> privacyArray = new ArrayList<>();
        privacyArray.add("Private");
        privacyArray.add("Public");
        Spinner privacyOptions = findViewById(R.id.PrivacyModeActive);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.privacy_view, privacyArray);
        privacyOptions.setAdapter(adapter);
        this.model = new RegistrationModel();
        updateUI();
    }

    public void updateUI() {
        DatabaseReference ref = model.getUserReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue(String.class);
                String email = dataSnapshot.child("Email").getValue(String.class);
                String phoneNumber = dataSnapshot.child("Phone").getValue(String.class);
                Boolean privacyModeActive = dataSnapshot.child("PrivacyModeActive").getValue(Boolean.class);
                Float rating = dataSnapshot.child("Rating").getValue(Float.class);
                String profilePicturePath = dataSnapshot.child("profilePicture").getValue(String.class);
                ((TextView)findViewById(R.id.Name)).setText(name);
                ((TextView)findViewById(R.id.Email)).setText(email);
                ((TextView)findViewById(R.id.PhoneNumber)).setText(phoneNumber);

                ((RatingBar) findViewById(R.id.ratingBar)).setRating(rating);




            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("UNABLE TO RETRIEVE DATA!!!!!");

            }
        });
    }

}
