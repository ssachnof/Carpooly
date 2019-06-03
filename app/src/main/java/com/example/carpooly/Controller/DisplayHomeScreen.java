package com.example.carpooly.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carpooly.Model.RideModel;
import com.example.carpooly.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DisplayHomeScreen extends AppCompatActivity {
    private RideModel rideModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        this.rideModel = new RideModel(this);
        Task<QuerySnapshot> task = rideModel.getRidesCollectionRef().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<RideModel> rideDetails = rideModel.getRides(queryDocumentSnapshots);
                for (RideModel ride : rideDetails){
                    addRideToPage(ride);
                }
            }
        });
    }

    public void openAccountPage(View view) {
        Intent intent = new Intent(this, AccountControl.class);
        startActivity(intent);
    }

    public void addRideToPage(RideModel ride){
        LinearLayout layout = ((LinearLayout)findViewById(R.id.RideDetails));
        TextView tv = new TextView(this);
        rideDetailsToText(ride, tv);
        layout.addView(tv);
        Button button = new Button(this);
        button.setText("Add to Ride");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("adding user for ride id: " + ride.getRideId());
            }
        });
        layout.addView(button);
    }

    private void rideDetailsToText(RideModel ride, TextView tv){
        String destination = ride.getDestination();
        String driverName = ride.getDriverName();
        Date departureDate = ride.getDepartureDate();
        Date departuretime = ride.getDepartureTime();
        String text = "destination: " + destination + "\n";
        text += "driverName: " + driverName + "\n";

        Calendar cal = Calendar.getInstance();
        cal.setTime(departureDate);
        String month = Integer.toString(cal.get(Calendar.MONTH));
        String year = Integer.toString(cal.get(Calendar.YEAR));
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        cal.setTime(departuretime);
        String hours = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
        String minutes = Integer.toString(cal.get(Calendar.MINUTE));
        if (minutes.length() == 1){
            minutes = "0" + minutes;
        }

        text += "Departure Date: " + month + "/" + day + "/" + year + "\n";
        text += "Departure Time: " + hours + ":" + minutes + "\n";
        tv.setText(text);


    }

    public void openCreateNewRidePage(View view) {
        Intent intent = new Intent(this, CreateRideActivity.class);
        startActivity(intent);
    }
}
