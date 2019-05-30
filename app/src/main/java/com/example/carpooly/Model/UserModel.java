package com.example.carpooly.Model;


import android.content.Context;

import com.example.carpooly.Controller.AccountControl;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public abstract class UserModel {

    private String email;
    private String password;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;


    public UserModel(String email, String password, Context context) {
        this.email = email;
        this.password = password;
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        Firebase.setAndroidContext(context);
    }

    public UserModel(Context context){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        Firebase.setAndroidContext(context);
    }

    public void setUser(){
        this.auth = FirebaseAuth.getInstance();
        this.user = auth.getCurrentUser();
    }

    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getUId(){return user.getUid();}
    public FirebaseUser getUser(){
        setUser();
        return user;
    }

    public FirebaseDatabase getDatabase(){
        this.database = FirebaseDatabase.getInstance();
        return this.database;
    }

    public FirebaseAuth getAuth(){
        this.auth = FirebaseAuth.getInstance();
        return auth;
    }

    public abstract void write();
    public abstract void read(String fieldName);
}
