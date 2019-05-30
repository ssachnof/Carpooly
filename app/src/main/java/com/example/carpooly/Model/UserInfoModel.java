package com.example.carpooly.Model;

import android.content.Context;
import android.provider.ContactsContract;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.google.firebase.storage.StorageReference;


// note that we may want to extend loginModel in the future instead
public class UserInfoModel extends UserModel {
    private String confirm_pass;
    private String name;
    private String phoneNumber;
    private String profilePicture;

    private FirebaseUser user;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    //todo: make sure that at some point you modify the security settings on the db so that a user
    // can't write to another one's data!!!!!!


    //todo: change privacy mode active to be stored as an integer value instead of a boolean!!!!!

    //todo: have controller pass in name as 1 field instead of 2

    public UserInfoModel(String email, String pass, String confirm_pass, String phoneNumber,
                         String firstName, String lastName, Context context) {
        super(email, pass, context);
        this.confirm_pass = confirm_pass;
        this.name = firstName + " " + lastName;//this should probably be done in controller!!!!!
        this.phoneNumber = phoneNumber;
        this.profilePicture = getDefaultProfilePicture();
        //fairly confident that we don't need this
        //this.auth = FirebaseAuth.getInstance();
        this.storage = FirebaseStorage.getInstance();
        this.database = FirebaseDatabase.getInstance();

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


    @Override
    public void write() {
        this.database = FirebaseDatabase.getInstance();
        this.user = getUser();
        DatabaseReference ref = database.getReference().child("Users").child(user.getUid());
        HashMapInitializer<String, Serializable> hashInitializer = new HashMapInitializer<>();
        HashMap<String, Serializable> userData = hashInitializer.
                makeHash(Arrays.asList("Name", "Rating", "Email", "Phone", "PrivacyModeActive"),
                        Arrays.<Serializable>asList(this.name, 3.0, super.getEmail(), this.phoneNumber, 0));
        ref.setValue(userData);
//        StorageReference storageRef = storage.getReference().child("images/" + profilePicture);
//        ref.child("profilePicture").setValue(storageRef.toString());
    }

    public void read(String fieldName){
        DatabaseReference userRef = getUserReference();


    }


    public DatabaseReference getUserReference(){
        this.user = super.getUser();
        return database.getReference().child("Users").child(user.getUid());
    }

    public DatabaseReference getChild(String fieldName){
        this.database = FirebaseDatabase.getInstance();
        this.user = getUser();
        DatabaseReference ref = getUserReference();
        return ref.child(fieldName);
    }
}