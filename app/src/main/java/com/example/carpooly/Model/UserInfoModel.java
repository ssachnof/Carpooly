package com.example.carpooly.Model;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;*/

import com.example.carpooly.DatabaseReader;
import com.example.carpooly.DatabaseWriter;
import com.example.carpooly.HashMapInitializer;
import com.example.carpooly.UserObject;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.storage.StorageReference;

import javax.annotation.Nullable;

import static com.firebase.ui.auth.ui.phone.CheckPhoneNumberFragment.TAG;


// note that we may want to extend loginModel in the future instead
public class UserInfoModel extends UserModel {
    private String confirm_pass;
    private String name;
    private String phoneNumber;
    private String profilePicture;

    private FirebaseUser user;
    private FirebaseStorage storage;
    private FirebaseFirestore database;
    //todo: make sure that at some point you modify the security settings on the db so that a user
    // can't writeOnRegistration to another one's data!!!!!!


    //todo: change privacy mode active to be stored as an integer value instead of a boolean!!!!!

    //todo: have controller pass in name as 1 field instead of 2

    public UserInfoModel(String email, String pass, String confirm_pass, String phoneNumber,
                         String firstName, String lastName, Context context) {
        super(email, pass, context);
        this.confirm_pass = confirm_pass;
        this.name = firstName + " " + lastName;//this should probably be done in controller!!!!!
        this.phoneNumber = phoneNumber;
        this.profilePicture = getDefaultProfilePicture();
        this.storage = FirebaseStorage.getInstance();
        this.database = FirebaseFirestore.getInstance();

    }

    public UserInfoModel(Context context){
        super(context);
    }

    public Task<AuthResult> registerUser() {
        return super.getAuth().createUserWithEmailAndPassword(super.getEmail(), super.getPassword());
    }


    private String getDefaultProfilePicture(){return "default_profile_picture.jpg";}

    public String getName(){return name;}

    public String getPhoneNumber(){return phoneNumber;}


    public void writeOnRegistration() {
        this.database = FirebaseFirestore.getInstance();
        this.user = getUser();
        Map<String, Object> userData = new HashMap<>();
        userData.put("Name", this.name);
        userData.put("Rating", "3.0");
        userData.put("Email", super.getEmail());
        userData.put("Phone", this.phoneNumber);
        userData.put("PrivacyMode", "Private");
        database.collection("Users").document(this.user.getUid()).
                set(userData, SetOptions.merge());//set options.merge prevents documents from being overwritten
    }

    public Map<String, Object> read(@Nullable DocumentSnapshot documentSnapshot,
                     @Nullable FirebaseFirestoreException e){
        if (e != null){
            Log.w(TAG,"Listen Failed!");
            throw new IllegalArgumentException();
        }

        if (documentSnapshot != null && documentSnapshot.exists()){
            return documentSnapshot.getData();
        }
        else{
            Log.w(TAG, "Current Data: null");
            throw new NullPointerException();
        }
    }
//    public DatabaseReference getUserReference(){
//        this.user = super.getUser();
//        return database.getReference().child("Users").child(user.getUid());
//    }

//    public DatabaseReference getChild(String fieldName){
//        this.database = FirebaseFirestore.getInstance()
//        this.user = getUser();
//        DatabaseReference ref = getUserReference();
//        return ref.child(fieldName);
//    }
}