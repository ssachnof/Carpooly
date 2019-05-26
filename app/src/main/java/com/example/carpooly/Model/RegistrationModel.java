package com.example.carpooly.Model;

import android.content.Context;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;*/

import com.example.carpooly.DatabaseWriter;
import com.example.carpooly.HashMapInitializer;
import com.example.carpooly.UserObject;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RegistrationModel implements DatabaseWriter {
    private String email;
    private String pass;
    private String confirm_pass;
    private String name;
    private String phoneNumber;

    private Context registrationContext;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private UserObject newUser;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    //todo: make sure that at some point you modify the security settings on the db so that a user
    // can't write to another one's data!!!!!!


    public RegistrationModel(String email, String pass, String confirm_pass, String phoneNumber,
                             String firstName, String lastName, Context registrationContext) {
        this.email = email;
        this.pass = pass;
        this.confirm_pass = confirm_pass;
        this.name = firstName + " " + lastName;
        this.phoneNumber = phoneNumber;
        this.registrationContext = registrationContext;
        this.auth = FirebaseAuth.getInstance();

    }
    public Task<AuthResult> registerUser() {
        return auth.createUserWithEmailAndPassword(email, pass);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return pass;
    }

    public String getName(){return name;}

    public String getPhoneNumber(){return phoneNumber;}

    public void setAuth() {
        this.auth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getUser() {
        this.user = auth.getCurrentUser();
        return user;
    }

    @Override
    public void write(){
        DatabaseReference ref = database.getReference().child("Users").child(user.getUid());
        HashMapInitializer<String, Serializable> hashInitializer = new HashMapInitializer<>();
        HashMap<String, Serializable> userData = hashInitializer.
                makeHash(Arrays.asList("Name", "Rating", "Email", "Phone", "PrivacyModeActive"),
                        Arrays.<Serializable>asList(this.name, 3.0, this.email, this.phoneNumber, true));
        ref.setValue(userData);
    }
}