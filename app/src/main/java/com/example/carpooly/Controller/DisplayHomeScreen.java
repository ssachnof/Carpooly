package com.example.carpooly.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carpooly.Model.RideModel;
import com.example.carpooly.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.firestore.QuerySnapshot;

public class DisplayHomeScreen extends AppCompatActivity {
    private RideModel rideModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginUserActivity.getUserEmailKey());

        //display the username
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText(username + " successfully logged in!!!!!!");
        View v = findViewById(R.id.rd);

        this.rideModel = new RideModel(this);
        Task<QuerySnapshot> task = rideModel.getRidesCollectionRef().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                addRideToPage("this is a test");
                addRideToPage("test2");
            }
        });
    }

    public void openAccountPage(View view) {
        Intent intent = new Intent(this, AccountControl.class);
        startActivity(intent);
    }

    public void addRideToPage(String text) {
        LinearLayout layout = ((LinearLayout)findViewById(R.id.RideDetails));
        TextView tv = new TextView(this);
        tv.setText(text + "\n\n");
        int numViews = layout.getChildCount();
        layout.addView(tv);

    }

    public void openCreateNewRidePage(View view) {
        Intent intent = new Intent(this, CreateRideActivity.class);
        startActivity(intent);
    }
}
