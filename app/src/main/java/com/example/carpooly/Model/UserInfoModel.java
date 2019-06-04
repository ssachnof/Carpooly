package com.example.carpooly.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;*/

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import static com.firebase.ui.auth.ui.phone.CheckPhoneNumberFragment.TAG;


// note that we may want to extend loginModel in the future instead
public class UserInfoModel extends UserModel {
    private String confirm_pass;
    private String name;
    private String phoneNumber;
    private String profilePicture;

    private static FirebaseUser user = getUser();
    private FirebaseStorage storage;
    private static FirebaseFirestore database = Database.getDBInstance(); // replaced line below - testing
    final private static CollectionReference usersCollectionRef = database.collection("Users");
    private static DocumentReference currentUserInfoDocRef = usersCollectionRef.document(user.getUid());
    //why is this an instance variable here?
    final private CollectionReference ridesCollectionRef = database.collection("Rides");
    private String userRating;
    private String privacyMode;

    public UserInfoModel(String email, String pass, String phoneNumber,
                         String firstName, String lastName, Context context) {
        super(email, pass, context);
        this.name = firstName + " " + lastName;//this should probably be done in controller!!!!!
        this.phoneNumber = phoneNumber;
        this.profilePicture = getDefaultProfilePicture();
        this.storage = Database.getStorageInstance();
        this.user = getUser();
        this.privacyMode = "Private";
        this.userRating = "3.0";
        this.currentUserInfoDocRef = database.collection("Users").document(user.getUid());
        Firebase.setAndroidContext(context);

    }

    public UserInfoModel(String email, String pass, String phoneNumber,
                         String firstName, String lastName, String privacyMode, String userRating,
                         Context context) {
        super(email, pass, context);
        this.name = firstName + " " + lastName;//this should probably be done in controller!!!!!
        this.phoneNumber = phoneNumber;
        this.profilePicture = getDefaultProfilePicture();
        this.storage = Database.getStorageInstance();
        this.user = getUser();
        this.privacyMode = privacyMode;
        this.userRating = userRating;
        this.currentUserInfoDocRef = database.collection("Users").document(user.getUid());
        Firebase.setAndroidContext(context);

    }

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
        userData.put("Password", super.getPassword());
        database.collection("Users").document(this.user.getUid()).
                set(userData, SetOptions.merge());//set options.merge prevents documents from being overwritten
    }

    public static UserInfoModel read(String UId, Context context){
//        Map<String, Object> userData = currentUserInfoDocRef.get().getResult().getData();
        DocumentReference docRef = usersCollectionRef.document(UId);
        Map<String, Object> userData = new HashMap<>();
        assert(usersCollectionRef != null);
        Task<DocumentSnapshot> snapshotTask = currentUserInfoDocRef.get();
        while(! snapshotTask.isSuccessful()){
        }
        userData.putAll(snapshotTask.getResult().getData());
        System.out.println(userData.keySet());
        System.out.println(userData.entrySet());
        String name = (String)userData.get("Name");
        System.out.println("Name: " + name);
        String firstName = name.split(" ")[0];
        String lastName = name.split(" ")[1];
        String userRating = (String)userData.get("Rating");
        String phoneNumber = (String)userData.get("Phone");
        String privacyMode = (String)userData.get("PrivacyMode");
        String email = (String)userData.get("Email");
        String password = (String)userData.get("Password");
        return new UserInfoModel(email, password, phoneNumber, firstName, lastName, privacyMode, userRating, context);
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

    public static CollectionReference getUsersCollection(){return usersCollectionRef;}
    public CollectionReference getRidesCollectionRef(){return ridesCollectionRef;}
    public void setName(String name){this.name = name;}
    public String getUserRating(){
        return this.userRating;
    }
    public static DocumentReference getCurrentUserInfoDocRef(){return currentUserInfoDocRef;}
    public String getPrivacyMode(){
        return this.privacyMode;
    }

}