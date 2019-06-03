package com.example.carpooly.Model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;

import static org.junit.Assert.*;

public class RideModelTest {

    @Test
    public void setDriverDisplayName() {
    }

    @Test
    public void getDestination() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            final Date departureDate = dateFormatter.parse("02/11/2019");
            RideModel rideModel = new RideModel("0", "Tom", departureDate, departureDate, "LA", 4);
            assertEquals(rideModel.getDestination(), "LA");
        }
        catch(ParseException e){
            System.out.println("ERROR FOUND WHILE PARSING!!!!");
            e.printStackTrace();
        }
    }

    @Test
    public void getDriverName() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            final Date departureDate = dateFormatter.parse("02/11/2019");
            RideModel rideModel = new RideModel("0", "Tom", departureDate, departureDate, "LA", 4);
            assertEquals("Tom", rideModel.getDriverName());
        }
        catch(ParseException e){
            System.out.println("ERROR FOUND WHILE PARSING!!!!");
            e.printStackTrace();
        }
    }

    @Test
    public void getDepartureDate() {
    }

    @Test
    public void getDepartureTime() {
    }
}