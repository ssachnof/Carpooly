package com.example.carpooly.Model;

import android.content.Context;

<<<<<<< HEAD
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
=======
import com.google.firebase.Timestamp;
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<String> riders;
    private Context context;


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
<<<<<<< HEAD
    }

    public RideModel(String driverId, String driverName, Date departureDate, Date departureTime, String destination,
                     int maxCapacity, String rideId){
=======
        this.riders = new ArrayList<>();
        this.context = context;
    }

    public RideModel(String driverId, String driverName, Date departureDate, Date departureTime, String destination,
                     int maxCapacity, String rideId, List<String> riders, Context context){
        super(context);
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
        this.driver = driverId;
        this.driverDisplayName = driverName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.maxCapacity = maxCapacity;
        this.destination = destination;
        this.rideId = rideId;
<<<<<<< HEAD
=======
        this.riders = riders;
        this.rideCollectionRef = super.getDatabase().collection("Rides");
        this.rideDocumentReference = rideCollectionRef.document(rideId);
        this.context = context;
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
    }
    public RideModel(Context context){
        super(context);
        this.rideCollectionRef = super.getDatabase().collection("Rides");
        this.context = context;
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
<<<<<<< HEAD
=======
        rideData.put("Riders", riders);
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
        rideDocumentReference.set(rideData, SetOptions.merge());
    }

    public ArrayList<UserInfoModel> read(){
        ArrayList<UserInfoModel> rides = new ArrayList<>();
        List<DocumentSnapshot> rideDocuments = rideCollectionRef.get().getResult().getDocuments();
        if (this.context == null){
            throw new NullPointerException();
        }
        for(DocumentSnapshot rideDocument : rideDocuments){
            RideModel ride = getRide(rideDocument, this.context);
            rides.add(ride);
        }
        return rides;
    }

    private RideModel getRide(DocumentSnapshot rideDocument, Context context){
        Map<String, Object> rideData = rideDocument.getData();
        String driverName = (String)rideData.get("DriverName");
        String driverId = (String)rideData.get("DriverId");
        long mc = (int)(long)rideData.get("MaxCapacity");
        Date departureDate = ((Timestamp)rideData.get("DepartureDate")).toDate();
        Date departureTime = ((Timestamp)rideData.get("DepartureTime")).toDate();
        String destination = (String)rideData.get("Destination");
        String rideId = (String)rideData.get("RideId");
<<<<<<< HEAD
        return new RideModel(driverId, driverName, departureDate, departureTime, destination, maxCapacity, rideId);
=======
        List<String> riders = (List<String>)rideData.get("Riders");
        if (this.context == null){
            throw new NullPointerException();
        }
        return new RideModel(driverId, driverName, departureDate, departureTime, destination, maxCapacity, rideId, riders, context);
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba

    }

    public List<RideModel> getRides(QuerySnapshot queryDocumentSnapshots){
        ArrayList<RideModel> rides = new ArrayList<>();
        List<DocumentSnapshot> rideDocuments = queryDocumentSnapshots.getDocuments();
        for(DocumentSnapshot rideDocument : rideDocuments){
            rides.add(getRide(rideDocument, this.context));
        }
        return rides;
    }
    public void setDriverDisplayName(){
        this.driverDisplayName = super.getName();
    }
    public DocumentReference getRideDocumentReference(){
        return this.rideDocumentReference;
    }
    public String getDestination(){return this.destination;}
    public String getDriverName(){return this.driverDisplayName;}
    public Date getDepartureDate(){return this.departureDate;}
    public Date getDepartureTime(){return this.departureTime;}
    public String getRideId(){return rideId;}
<<<<<<< HEAD

//    @Override
//    public ArrayList<UserInfoModel> read(){
//
//
//    }
=======
    public CollectionReference getRideCollectionRef(){return rideCollectionRef;}
    public void addRider(){
        Rider rider = new Rider(context, super.getName(), super.getUId());
        rider.getRiderName(rideDocumentReference);
//        System.out.println("Name: " + name);
//        rideDocumentReference.update("Riders", FieldValue.arrayUnion(name));
    }
    public List<String> getRiders(){return riders;}//will eventually want to return rider objects
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
}
