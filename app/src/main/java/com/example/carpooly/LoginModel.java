package com.example.carpooly;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


class LoginModel {
    private String email;
    private String password;
    private FirebaseAuth auth;
    private Context loginContext;
    private FirebaseUser currentUser;

    LoginModel(String email, String pass, Context context){
        this.email = email;
        this.password = pass;
        this.auth = FirebaseAuth.getInstance();
        this.loginContext = context;
        this.currentUser = auth.getCurrentUser();
        Firebase.setAndroidContext(context);
    }

    public FirebaseUser getCurrentUser(){
        this.currentUser = auth.getCurrentUser();
        return currentUser;
    }

    public void setAuth(){
        this.auth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getAuth(){return auth;}

    public String getEmail(){return email;}

    //returns a task representing the user's attempt to login
    public Task<AuthResult> signInUser() {
        return auth.signInWithEmailAndPassword(email, password);
    }


}