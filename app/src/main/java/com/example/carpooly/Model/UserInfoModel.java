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
import com.example.carpooly.Search;
import com.example.carpooly.UserObject;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
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
import java.lang.reflect.Array;
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
    private FirebaseFirestore database = Database.getDBInstance(); // replaced line below - testing
    private DocumentReference currentUserInfoDocRef;
    final private CollectionReference usersCollectionRef = database.collection("Users");
    final private CollectionReference ridesCollectionRef = database.collection("Rides");
    private String userRating;
    private String privacyMode;

    public UserInfoModel(String email, String pass, String confirm_pass, String phoneNumber,
                         String firstName, String lastName, Context context) {
        super(email, pass, context);
        this.confirm_pass = confirm_pass;
        this.name = firstName + " " + lastName;//this should probably be done in controller!!!!!
        this.phoneNumber = phoneNumber;
        this.profilePicture = getDefaultProfilePicture();
        this.storage = Database.getStorageInstance();
        this.user = getUser();
        this.privacyMode = "Private";
        this.userRating = "3.0";
        this.currentUserInfoDocRef = database.collection("Users").document(user.getUid());

    }

    public UserInfoModel(Context context){
        super(context);
        this.user = super.getAuth().getCurrentUser();
        this.currentUserInfoDocRef = database.collection("Users").document(user.getUid());

    }

    public UserInfoModel(){}

    public Task<AuthResult> registerUser() {
        return super.getAuth().createUserWithEmailAndPassword(super.getEmail(), super.getPassword());
    }


    private String getDefaultProfilePicture(){return "default_profile_picture.jpg";}

    public String getName(){return name;}

    public String getPhoneNumber(){return phoneNumber;}


    public void writeOnRegistration() {
        this.user = getUser();
        Map<String, Object> userData = new HashMap<>();
        userData.put("Name", this.name);
        userData.put("Rating", this.userRating);
        userData.put("Email", super.getEmail());
        userData.put("Phone", this.phoneNumber);
        userData.put("PrivacyMode", this.privacyMode);
        userData.put("UserId", super.getUId());
        database.collection("Users").document(this.user.getUid()).
                set(userData, SetOptions.merge());//set options.merge prevents documents from being overwritten
    }

    public ArrayList<UserInfoModel> read(){
        Map<String, Object> userData = currentUserInfoDocRef.get().getResult().getData();
        this.name = (String)userData.get("Name");
        this.userRating = (String)userData.get("Rating");
        this.phoneNumber = (String)userData.get("Phone");
        this.privacyMode = (String)userData.get("PrivacyMode");
        ArrayList<UserInfoModel> output = new ArrayList<>();
        output.add(this);
        return output;
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

    public CollectionReference getUsersCollection(){return usersCollectionRef;}
    public CollectionReference getRidesCollectionRef(){return ridesCollectionRef;}
//    public void setName(String name){this.name = name;}

}