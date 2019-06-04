package com.example.carpooly.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carpooly.Model.RideModel;
import com.example.carpooly.Model.UsersSearch;
import com.example.carpooly.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

public class CreateRideActivity extends AppCompatActivity {

    private RideModel rideModel;
    private UsersSearch usersSearcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ride);
        Intent intent = getIntent();
    }


    public void createNewRide(View view){
        String destination = ((EditText) findViewById(R.id.DestinationCity)).getText().toString();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        EditText depDate = ((EditText)findViewById(R.id.DepartureDate));
        EditText depTime = ((EditText)findViewById(R.id.DepartureTime));
        try {
            Date departureDate = dateFormatter.parse(depDate.getText().toString());
            Date departureTime = dateFormatter.parse(depTime.getText().toString());
            RideModel rideModel = new RideModel(departureDate, departureTime, destination, this);
            rideModel.write();
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, DisplayHomeScreen.class);
        startActivity(intent);
    }
}
