package com.example.carpooly.Model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.carpooly.Model.*;
import com.example.carpooly.Controller.*;
import com.google.firebase.database.DatabaseReference;


public class LoginModel extends UserModel{
    private FirebaseUser user;

    public LoginModel(String email, String pass, Context context){
        super(email, pass, context);
        this.user = super.getUser();
    }


    public Task<AuthResult> signInUser() {
        return super.getAuth().signInWithEmailAndPassword(super.getEmail(), super.getPassword());
    }


    // note that idk if this methods will actually have anything in them
    // we may want to change them to interfaces later. idk
    public void write(){
    }

    public void read(String fieldName){

    }

    public DatabaseReference getCurrentUserPath(){
        return super.getDatabase().getReference("Users/").child(super.getUId());
    }

}