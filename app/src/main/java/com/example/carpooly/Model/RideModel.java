package com.example.carpooly.Model;

import android.content.Context;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class RideModel{
    private String driverId;
    private Date departureDate;
    private Date departureTime;
    private String rideId;
    private int maxCapacity; //--this will eventually be included in form, for now just set as a constant
    private String driverDisplayName;
    private double estimatedCost;//cost of the gas money to be implemented later
    private DocumentReference rideDocumentReference;
    private final static CollectionReference rideCollectionRef = Database.getDBInstance().collection("Rides");
    private String destination;
    private List<Rider> riders;
    private Context context;
    private UserInfoModel driverInfo;
    private Driver driver;

    public RideModel(Date departureDate, Date departureTime, String destination, Context context){
        this.driverId = UserModel.getUser().getUid();
        this.departureDate = departureDate;
        this.maxCapacity = 4;
        this.departureTime = departureTime;
        this.destination = destination;
        this.rideDocumentReference = rideCollectionRef.document();
        this.rideId = rideDocumentReference.getId();
        this.riders = new ArrayList<>();
        this.context = context;
        UsersSearch search = new UsersSearch(context, driverId);
        this.driver = new Driver(context, driverId);
        Firebase.setAndroidContext(context);

    }

    private RideModel(Date departureDate, Date departureTime, String destination,
                      Driver driver, List<Rider> riders, Context context){

    }

    public void write(){
        Map<String, Object> rideData = new HashMap<>();
        rideData.put("DriverName", driverDisplayName);//in order for this not to be null, you will have to query the users collection
        rideData.put("DriverId", driverId);
        rideData.put("MaxCapacity", maxCapacity);
        rideData.put("DepartureDate", departureDate);
        rideData.put("DepartureTime", departureTime);
        rideData.put("Destination", destination);
        rideData.put("RideId", rideId);
        rideData.put("Riders", riders);
        rideDocumentReference.set(rideData, SetOptions.merge());
    }

    public static RideModel read(DocumentReference rideDocument, Context context){
        Map<String, Object> rideData = rideDocument.get().getResult().getData();
        Driver driver = getDriver(rideData, context);//not entirely sure if you want to put this function in ridemodel or driver
        String destination = (String)rideData.get("Destination");
        Date departureDate = ((Timestamp)rideData.get("DepartureDate")).toDate();
        Date departureTime = ((Timestamp)rideData.get("DepartureTime")).toDate();
        List<Rider> riders = getRiders(rideData, context);
        return new RideModel(departureDate, departureTime, destination, driver, riders, context);
    }
    public DocumentReference getRideDocumentReference(){
        return this.rideDocumentReference;
    }
    public String getDestination(){return this.destination;}
    public String getDriverName(){return this.driverDisplayName;}
    public Date getDepartureDate(){return this.departureDate;}
    public Date getDepartureTime(){return this.departureTime;}
    public String getRideId(){return rideId;}
    public List<Rider> getRiders(){return this.riders;}
    public static CollectionReference getRideCollectionRef(){return rideCollectionRef;}
    //this should be handled in the riders class
    public void addRider(){
        Rider rider = new Rider(context, UserInfoModel.getUser().getUid());
        this.riders.add(rider);
    }

    //this should probably be in RideModel
    private static List<Rider> getRiders(Map<String, Object> rideData, Context context){
        List<String> riderIds = (List<String>)rideData.get("Riders");
        List<Rider> riders = new ArrayList<>();

        for (String riderId : riderIds){
            Rider rider = new Rider(context, riderId);
            riders.add(rider);
        }
        return riders;
    }

    private static Driver getDriver(Map<String, Object> rideData, Context context){
        String driverId = (String)rideData.get("DriverId");
        return new Driver(context, driverId);
    }

    public static List<RideModel> getRides(Context context){
        List<RideModel> rides = new ArrayList<>();
        List<DocumentSnapshot> rideDocuments = new ArrayList<>();
        Task<QuerySnapshot> snapshot = getRideCollectionRef().get();
        while(!snapshot.isSuccessful()){

        }
        List<DocumentSnapshot> docs =  snapshot.getResult().getDocuments();
        for (DocumentSnapshot )
//        rideCollectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                queryDocumentSnapshots.getDocuments();
//                rideDocuments.addAll(queryDocumentSnapshots.getDocuments());
//            }
//        });
        for (DocumentSnapshot rideDoc : rideDocuments){
            rides.add(read(rideDoc.getReference(), context));
        }
        return rides;
    }
}
