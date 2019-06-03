package com.example.carpooly.Model;

import android.content.Context;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.model.Document;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class RideModel extends UsersSearch{
    private String driver;
    private ArrayList<String> passengers;
    private Date departureDate;
    private Date departureTime;
    private String rideId;
    private int maxCapacity; //--this will eventually be included in form, for now just set as a constant
    private String driverDisplayName;
    private double estimatedCost;//cost of the gas money to be implemented later
    private DocumentReference rideDocumentReference;
    private CollectionReference rideCollectionRef;
    private String destination;


    public RideModel(Date departureDate, Date departureTime, String DriverName, String destination, Context context){
        super(context);
        this.driver = super.getUId();
        this.departureDate = departureDate;
        this.maxCapacity = 4;
        this.passengers = new ArrayList<>();
        this.departureTime = departureTime;
        this.driverDisplayName = DriverName;
        this.destination = destination;
        this.rideCollectionRef = super.getDatabase().collection("Rides");
        this.rideDocumentReference = rideCollectionRef.document();
        this.rideId = rideDocumentReference.getId();
    }

    public RideModel(String driverId, String driverName, Date departureDate, Date departureTime, String destination,
                     int maxCapacity, String rideId){
        this.driver = driverId;
        this.driverDisplayName = driverName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.maxCapacity = maxCapacity;
        this.destination = destination;
        this.rideId = rideId;
    }
    public RideModel(Context context){
        super(context);
        this.rideCollectionRef = super.getDatabase().collection("Rides");
    }
    public void write(){
        Map<String, Object> rideData = new HashMap<>();
        rideData.put("DriverName", driverDisplayName);//in order for this not to be null, you will have to query the users collection
        rideData.put("DriverId", driver);
        rideData.put("MaxCapacity", maxCapacity);
        rideData.put("DepartureDate", departureDate);
        rideData.put("DepartureTime", departureTime);
        rideData.put("Destination", destination);
        rideData.put("RideId", rideId);
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
        long mc = (int)(long)rideData.get("MaxCapacity");
        Date departureDate = ((Timestamp)rideData.get("DepartureDate")).toDate();
        Date departureTime = ((Timestamp)rideData.get("DepartureTime")).toDate();
        String destination = (String)rideData.get("Destination");
        String rideId = (String)rideData.get("RideId");
        return new RideModel(driverId, driverName, departureDate, departureTime, destination, maxCapacity, rideId);

    }

    public List<RideModel> getRides(QuerySnapshot queryDocumentSnapshots){
        ArrayList<RideModel> rides = new ArrayList<>();
        List<DocumentSnapshot> rideDocuments = queryDocumentSnapshots.getDocuments();
        for(DocumentSnapshot rideDocument : rideDocuments){
            rides.add(getRide(rideDocument));
        }
        return rides;
    }
    public void setDriverDisplayName(){
        this.driverDisplayName = super.getName();
    }

    public String getDestination(){return this.destination;}
    public String getDriverName(){return this.driverDisplayName;}
    public Date getDepartureDate(){return this.departureDate;}
    public Date getDepartureTime(){return this.departureTime;}
    public String getRideId(){return rideId;}

//    @Override
//    public ArrayList<UserInfoModel> read(){
//
//
//    }
}
