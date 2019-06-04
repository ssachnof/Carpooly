package com.example.carpooly.Model;


import android.content.Context;

import com.example.carpooly.Controller.AccountControl;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public abstract class UserModel {

    private String email;
    private String password;
    private static FirebaseAuth auth;
    private static FirebaseUser user;
    private FirebaseFirestore database;
    public UserModel(String email, String password, Context context) {
        this.email = email;
        this.password = password;
        auth = getAuth();
        this.database = getDatabase();
        user = auth.getCurrentUser();
        Firebase.setAndroidContext(context);
    }

    public UserModel(Context context){
        auth = getAuth();
        this.database = getDatabase();
        user = auth.getCurrentUser();
        //todo: we should probably read from the db here and set fields!!!!!
        Firebase.setAndroidContext(context);
    }

    //this only exists because a build failed
    public UserModel(){
        auth = getAuth();
    user = auth.getCurrentUser();}

    private static void setUser(){
        auth = getAuth();
        user = auth.getCurrentUser();
    }

    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getUId(){return user.getUid();}
    public static FirebaseUser getUser(){
        setUser();
        return user;
    }

    public FirebaseFirestore getDatabase(){
        this.database = Database.getDBInstance();
        return this.database;
    }

    public static FirebaseAuth getAuth(){
        auth = Database.getAuthInstance();
        return auth;
    }

//    public abstract void writeOnRegistration();
//    public abstract void read(String fieldName);
}
