package com.example.carpooly.Model;

import android.content.Context;

import com.example.carpooly.Search;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class UsersSearch {
    private final CollectionReference usersRef = UserInfoModel.getUsersCollection();
    private String driverName;
    private Context context;
    private UserInfoModel userInfo;
    public UsersSearch(Context context, String UId){
        this.context = context;
        this.userInfo = getUserData(UId);

    }
    public UsersSearch(){super();}


    private UserInfoModel getUserData(String UId){
        DocumentReference userDocRef = usersRef.document(UId);
        Map<String, Object> userData = new HashMap<>();
        userDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                userData.putAll(documentSnapshot.getData());
            }
        });
        System.out.println("Map: " + userData.entrySet());
        String email = (String)userData.get("Email");
        String name = (String)userData.get("Name");
        String phoneNumber = (String)userData.get("Phone");
        System.out.println("Name: "+ name);
        String firstName = name.split(" ")[0];
        String lastName = name.split(" ")[1];
        String password = (String)userData.get("Password");
        assert (context != null);
        return new UserInfoModel(email, password, phoneNumber, firstName, lastName, context);
    }

    public UserInfoModel getUserInfo(){
        assert (userInfo != null);
        return userInfo;
    }

//    //searches for a specific user
//    public Query queryCollection(String UId){
//        Query findUserQuery = usersRef.whereEqualTo("UserId", UId);
//        return findUserQuery;
//    }

    public CollectionReference getRef(){return usersRef;}

//    public Task<QuerySnapshot> getName(String UId){
//        Task<QuerySnapshot> userData = queryCollection(UId);
//        return userData;
//    }

//    public String getName(QuerySnapshot queryDocumentSnapshots){
//        System.out.println("keys: " + queryDocumentSnapshots.getDocuments().get(0).getData().keySet());
//        String driverName = (String)queryDocumentSnapshots.getDocuments().get(0).get("Name");
//        return driverName;
//
//
//
//    }
    }
