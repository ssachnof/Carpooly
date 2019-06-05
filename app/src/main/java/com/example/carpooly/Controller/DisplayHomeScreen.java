package com.example.carpooly.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carpooly.Model.RideModel;
import com.example.carpooly.Model.Rider;
import com.example.carpooly.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

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
//                rider.getUsersCollection().addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                        String name =(String)queryDocumentSnapshots.getDocuments().get(0).get("Name");
//                        System.out.println("RIDER2: " + name);
//                        ride.addRider();
//                        rider.getName();
//                    }
//                });
                ride.addRider();
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
        List<String> riders = ride.getRiders();
//        for (String rider : riders){
//            System.out.println("RIDER NAME: " + rider);
//        }
        if (minutes.length() == 1){
            minutes = "0" + minutes;
        }

        text += "Departure Date: " + month + "/" + day + "/" + year + "\n";
        text += "Departure Time: " + hours + ":" + minutes + "\n";
        text+= "Riders: ";
        for(String rider : riders){
            text += rider;
        }
        tv.setText(text);


    }

    public void openCreateNewRidePage(View view) {
        Intent intent = new Intent(this, CreateRideActivity.class);
        startActivity(intent);
    }
}
