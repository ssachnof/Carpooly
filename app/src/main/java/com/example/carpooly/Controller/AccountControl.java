package com.example.carpooly.Controller;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.carpooly.Model.UserInfoModel;
import com.example.carpooly.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

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
        DatabaseReference emailRef = model.getChild("Email");
        DatabaseReference nameRef = model.getChild("Name");
        DatabaseReference phoneRef = model.getChild("Phone");
        DatabaseReference ratingBarRef = model.getChild("Rating");

        emailRef.addValueEventListener(getDBChange("Email", String.class));
        nameRef.addValueEventListener(getDBChange("Name", String.class));
        phoneRef.addValueEventListener(getDBChange("Phone", String.class));
        ratingBarRef.addValueEventListener(getDBChange("Rating", float.class));
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ImageView profilePicture = ((ImageView)findViewById(R.id.ProfilePicture));
        profilePicture.setImageResource(R.drawable.carpooly3);
        // Get the Intent that started this activity and extract the string

//        //set the privacy mode
//        List<String> privacyArray = new ArrayList<>();
//        privacyArray.add("Private");
//        privacyArray.add("Public");
//        Spinner privacyOptions = findViewById(R.id.PrivacyModeActive);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.privacy_view, privacyArray);
//        DatabaseReference uprivmode = currentUser.child("PrivacyModeActive");
//        privacyOptions.setAdapter(adapter);
//        uprivmode.addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot){
//                Spinner privacyOptions = findViewById(R.id.PrivacyModeActive);
//                privacyOptions.setSelection(dataSnapshot.getValue(int.class));
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError){
//                LOGGER.log(Level.WARNING, "DATABASE ERROR: " + "STUFF GOT CANCELLED");
//            }
//        });
//    }
//
//
    }
    public <T extends Object> ValueEventListener getDBChange(final String field, final Class<T> className){
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (field.equals("Email")){
                    TextView userEmail = ((TextView) findViewById(R.id.Email));
                    userEmail.setText(dataSnapshot.getValue(String.class));

                }
                else if (field.equals("Name")){
                    TextView userEmail = ((TextView) findViewById(R.id.Name));
                    userEmail.setText(dataSnapshot.getValue(String.class));

                }

                else if(field.equals("Password")){
                    LOGGER.log(Level.SEVERE, "ACCOUNT CONTROL: PASSWORD VALUE LISTENTER UNIMPLEMENTED");
                    throw new IllegalArgumentException();
                }

                else if (field.equals("Phone")){
                    TextView userPhone = ((TextView) findViewById(R.id.PhoneNumber));
                    userPhone.setText(dataSnapshot.getValue(String.class));
                }

                else if (field.equals("Rating")){
                    RatingBar userRating = ((RatingBar) findViewById(R.id.ratingBar));
                    userRating.setRating(dataSnapshot.getValue(float.class));

                }

                else if (field.equals("PrivacyModeActive")){
                    Spinner privacyOptions = findViewById(R.id.PrivacyModeActive);
                    privacyOptions.setSelection(dataSnapshot.getValue(int.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                LOGGER.log(Level.SEVERE, "ACCOUNT CONTROL: DATABASE ERROR");
                throw new IllegalArgumentException();

            }
        };

    }

}
