package com.example.carpooly.Model;

import android.content.Context;

import com.example.carpooly.Search;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsersSearch extends UserInfoModel implements Search {
    private final CollectionReference usersRef = super.getUsersCollection();
    private String driverName;
    public UsersSearch(Context context){
        super(context);
    }
    public UsersSearch(){}
//    public Task<QuerySnapshot> queryCollection() {
//        List<DocumentSnapshot> userDocuments=  usersRef.get().getResult().getDocuments();
//        ArrayList<Map<String, Object>> usersData = new ArrayList<>();
//
//        for (DocumentSnapshot userDoc : userDocuments){
//            usersData.add(userDoc.getData());
//        }
//        return usersData;
//    }
    //searches for a specific user
    public Task<QuerySnapshot> queryCollection(String UId){
        Query findUserQuery = usersRef.whereEqualTo("UserId", UId);
        return findUserQuery.get();
    }

    public CollectionReference getRef(){return usersRef;}

//    public Task<QuerySnapshot> getDriverName(String UId){
//        Task<QuerySnapshot> userData = queryCollection(UId);
//        return userData;
//    }

    public String getDriverName(QuerySnapshot queryDocumentSnapshots){
        System.out.println("keys: " + queryDocumentSnapshots.getDocuments().get(0).getData().keySet());
        String driverName = (String)queryDocumentSnapshots.getDocuments().get(0).get("Name");
        System.out.println("Driver Name 2: " + driverName);
        return driverName;



    }}
