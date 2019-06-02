package com.example.carpooly.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.carpooly.Model.RideModel;
import com.example.carpooly.Model.UsersSearch;
import com.example.carpooly.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        this.usersSearcher = new UsersSearch(this);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        EditText depDateField = ((EditText)findViewById(R.id.DepartureDate));
        EditText depTimeField = ((EditText)findViewById(R.id.DepartureTime));
        final EditText destination = ((EditText) findViewById(R.id.DestinationCity));

        try {
            final Date departureDate = dateFormatter.parse(depDateField.getText().toString());
            dateFormatter = new SimpleDateFormat("HH:mm");
            final Date departureTime = dateFormatter.parse(depTimeField.getText().toString());
            final Context activityContext = this;
            //todo: you need to create functions inside model that 1 creates the query and 2 grabs the result from
            // the query snapshot
            Task<QuerySnapshot> driversQuery = usersSearcher.getUsersCollection().whereEqualTo("UserId", usersSearcher.getUId()).
                    get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    String driverName = (String)queryDocumentSnapshots.getDocuments().get(0).get("Name");
                    RideModel newRideModel = new RideModel(departureDate, departureTime, driverName,
                            destination.getText().toString(), activityContext);
                    newRideModel.write();
                }
            });
            Intent intent = new Intent(this, DisplayHomeScreen.class);
            startActivity(intent);

        }
        catch(ParseException e){
            System.out.println("ERROR FOUND WHILE PARSING!!!!");
            e.printStackTrace();
        }
    }
}
