package com.example.carpooly.Model;

import android.content.Context;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class RideModel extends UserInfoModel{
    private String driver;
    private ArrayList<String> passengers;
    private Date departureDate;
    private Date departureTime;
    private int maxCapacity; //--this will eventually be included in form, for now just set as a constant
    private String driverDisplayName;
    private double estimatedCost;//cost of the gas money to be implemented later
    private DocumentReference rideDocumentReference;
    private CollectionReference rideCollectionRef;


    public RideModel(Date departureDate, Date departureTime, Context context){
        super(context);
        this.driver = super.getUId();
        this.driverDisplayName = super.getName();
        this.departureDate = departureDate;
        this.maxCapacity = 4;
        this.passengers = new ArrayList<>();
        this.departureTime = departureTime;
        this.rideCollectionRef = super.getDatabase().collection("Rides");
        this.rideDocumentReference = rideCollectionRef.document();
        write();
    }

    public RideModel(String driverId, String driverName, Date departureDate, Date departureTime,
                     int maxCapacity){
        this.driver = driverId;
        this.driverDisplayName = driverName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.maxCapacity = maxCapacity;
    }
    public RideModel(Context context){
        super(context);
        this.rideCollectionRef = super.getDatabase().collection("Rides");
    }
    public void write(){
        Map<String, Object> rideData = new HashMap<>();
        setDriverDisplayName();
        rideData.put("DriverName", driverDisplayName);//in order for this not to be null, you will have to query the users collection
        rideData.put("DriverId", driver);
        rideData.put("MaxCapacity", maxCapacity);
        rideData.put("DepartureDate", departureDate);
        rideData.put("DepartureTime", departureTime);
        rideDocumentReference.set(rideData, SetOptions.merge());
    }

    public ArrayList<UserInfoModel> read(){
        ArrayList<UserInfoModel> rides = new ArrayList<>();
        List<DocumentSnapshot> rideDocuments = rideCollectionRef.get().getResult().getDocuments();
        for(DocumentSnapshot rideDocument : rideDocuments){
            RideModel ride = getRide(rideDocument);
            rides.add(ride);
        }
        return rides;
    }

    private RideModel getRide(DocumentSnapshot rideDocument){
        Map<String, Object> rideData = rideDocument.getData();
        String driverName = (String)rideData.get("DriverName");
        String driverId = (String)rideData.get("DriverId");
        int maxCapacity = (int)rideData.get("MaxCapacity");
        Date departureDate = (Date)rideData.get("DepartureDate");
        Date departureTime = (Date)rideData.get("DepartureTime");
        return new RideModel(driverId, driverName, departureDate, departureTime, maxCapacity);

    }

    public void setDriverDisplayName(){
        this.driverDisplayName = super.getName();
    }


//    @Override
//    public ArrayList<UserInfoModel> read(){
//
//
//    }
}
