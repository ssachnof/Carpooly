package com.example.carpooly.Controller;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.MenuItem;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


import com.example.carpooly.Model.UserInfoModel;
import com.example.carpooly.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

import javax.annotation.Nullable;

public class AccountControl extends AppCompatActivity {

    private UserInfoModel model;
    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ArrayAdapter<String> privacyAdapter;
    //todo: you need to make sure stuff goes into the correct class here!!!!!!
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        List<String> privacyArray = new ArrayList<>();
        privacyArray.add("Private");
        privacyArray.add("Public");
        this.privacyAdapter = new ArrayAdapter<>(this, R.layout.privacy_view, privacyArray);
        Spinner privacyOptions = ((Spinner) findViewById(R.id.PrivacyModeActive));
        privacyOptions.setAdapter(privacyAdapter);
        this.model = new UserInfoModel(this);
        final DocumentReference userDataRef = model.getDatabase().collection("Users").
                document(model.getUId());
        userDataRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                try {
                    Map<String, Object> userData = model.read(documentSnapshot, e);
                    String email = (String)userData.get("Email");
                    String name = (String)userData.get("Name");
                    float rating = Float.parseFloat((String)userData.get("Rating"));
                    String phoneNumber = (String)userData.get("Phone");
                    String privacyMode = (String)userData.get("PrivacyMode");
                    setViewObjects(email, name, rating, phoneNumber, privacyMode);
                    ImageView profilePicture = ((ImageView)findViewById(R.id.ProfilePicture));
                    profilePicture.setImageResource(R.drawable.carpooly3);


                }
                catch (RuntimeException e1){
                    System.out.println("Unable to listen to user data!!!!");
                    e1.printStackTrace();
                }


            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navbar);

        bottomNavigationView.setSelectedItemId(R.id.action_account);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                openHomePage();

                            case R.id.action_myRides:

                            case R.id.action_account:

                        }
                        return true;
                    }
                });


        privacyOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userDataRef.update("PrivacyMode", adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    public void setViewObjects(String email, String name, float rating,
                               String phone, String privacyMode){
        TextView userEmail = ((TextView) findViewById(R.id.Email));
        userEmail.setText(email);

        TextView userName = ((TextView) findViewById(R.id.Name));
        userName.setText(name);

        RatingBar userRating = ((RatingBar) findViewById(R.id.ratingBar));
        userRating.setRating(rating);

        TextView userPhone = ((TextView) findViewById(R.id.PhoneNumber));
        userPhone.setText(phone);

        Spinner userPrivacy = ((Spinner) findViewById(R.id.PrivacyModeActive));
        if (privacyMode.equals("Public")){
            userPrivacy.setSelection(1);
        }
        else{
            userPrivacy.setSelection(0);
        }

    }

    public void openHomePage(){
        Intent intent = new Intent(this, DisplayHomeScreen.class);
        startActivity(intent);
    }

}