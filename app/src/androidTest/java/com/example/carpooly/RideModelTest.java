package com.example.carpooly;

import com.example.carpooly.Model.RideModel;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class RideModelTest {
/* Test currently unused because function is unused */
//    @Test
//    public void setDriverDisplayName() {
//    }

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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            final Date departureDate = dateFormatter.parse("02/11/2019");
            RideModel rideModel = new RideModel("0", "Tom", departureDate, departureDate, "LA", 4);
            assertEquals(departureDate, rideModel.getDepartureDate());
        }
        catch(ParseException e){
            System.out.println("ERROR FOUND WHILE PARSING!!!!");
            e.printStackTrace();
        }
    }

    @Test
    public void getDepartureTime() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            final Date departureDate = dateFormatter.parse("02/11/2019");
            RideModel rideModel = new RideModel("0", "Tom", departureDate, departureDate, "LA", 4);
            assertEquals(departureDate, rideModel.getDepartureTime());
        }
        catch(ParseException e){
            System.out.println("ERROR FOUND WHILE PARSING!!!!");
            e.printStackTrace();
        }
    }
}