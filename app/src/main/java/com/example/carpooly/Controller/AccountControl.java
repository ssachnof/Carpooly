package com.example.carpooly.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;


import com.example.carpooly.Model.RegistrationModel;
import com.example.carpooly.Model.UserModel;
import com.example.carpooly.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.*;

public class AccountControl extends AppCompatActivity {

    private RegistrationModel model;
    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    //todo: you need to make sure stuff goes into the correct class here!!!!!!
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        // Get the Intent that started this activity and extract the string
        String email = "sJohnston@gmail.com";
        String phone = "(805) 867-5309";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference currentUser = database.getReference("Users/").child(UserModel.user.getUid());

        //set the image
        ImageView picture = findViewById(R.id.imageView2);
        picture.setImageResource(R.drawable.carpooly3);
        //todo: change adding these event listeners into a function
        //set the username
        DatabaseReference uname = currentUser.child("Name");
        uname.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                TextView userName = findViewById(R.id.textView3);
                userName.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                LOGGER.log(Level.WARNING, "DATABASE ERROR: " + "STUFF GOT CANCELLED");
            }
        });

        //set the rating
        DatabaseReference urate = currentUser.child("Rating");
        urate.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                RatingBar userRating = findViewById(R.id.ratingBar);
                float rate = dataSnapshot.getValue(float.class);
                userRating.setRating(rate);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                LOGGER.log(Level.WARNING, "DATABASE ERROR: " + "STUFF GOT CANCELLED");

            }
        });

        //set the email
        DatabaseReference uemail = currentUser.child("Email");
        uemail.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                TextView userEmail = findViewById(R.id.textView6);
                userEmail.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                LOGGER.log(Level.WARNING, "DATABASE ERROR: " + "STUFF GOT CANCELLED");
            }
        });

        //set the phone
        DatabaseReference uphone = currentUser.child("Phone");
        uphone.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                TextView userPhone = findViewById(R.id.textView9);
                userPhone.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                LOGGER.log(Level.WARNING, "DATABASE ERROR: " + "STUFF GOT CANCELLED");
            }
        });
        //set the privacy mode
        List<String> privacyArray = new ArrayList<>();
        privacyArray.add("Private");
        privacyArray.add("Public");
        Spinner privacyOptions = findViewById(R.id.PrivacyModeActive);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.privacy_view, privacyArray);
        DatabaseReference uprivmode = currentUser.child("PrivacyModeActive");
        privacyOptions.setAdapter(adapter);
        uprivmode.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                Spinner privacyOptions = findViewById(R.id.spinner);
                privacyOptions.setSelection(dataSnapshot.getValue(int.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                LOGGER.log(Level.WARNING, "DATABASE ERROR: " + "STUFF GOT CANCELLED");
            }
        });
    }

}
