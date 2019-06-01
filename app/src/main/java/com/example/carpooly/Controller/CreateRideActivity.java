package com.example.carpooly.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.carpooly.Model.RideModel;
import com.example.carpooly.R;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateRideActivity extends AppCompatActivity {

    private RideModel rideModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ride);
        Intent intent = getIntent();
    }


    public void createNewRide(View view){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        EditText depDateField = ((EditText)findViewById(R.id.DepartureDate));
        EditText depTimeField = ((EditText)findViewById(R.id.DepartureTime));

        try {
            Date departureDate = dateFormatter.parse(depDateField.getText().toString());
            dateFormatter = new SimpleDateFormat("HH:mm");
            Date departureTime = dateFormatter.parse(depTimeField.getText().toString());
            new RideModel(departureDate, departureTime, this);

        }
        catch(ParseException e){
            System.out.println("ERROR FOUND WHILE PARSING!!!!");
            e.printStackTrace();
        }
    }
}
