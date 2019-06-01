package com.example.carpooly.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carpooly.R;

public class DisplayLoginMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginUserActivity.getUserEmailKey());

        //display the username
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText(username + " successfully logged in!!!!!!");
    }

    public void openAccountPage(View view){
        Intent intent = new Intent(this, AccountControl.class);
        startActivity(intent);
    }

    public void openCreateNewRidePage(View view){
        Intent intent = new Intent(this, CreateRideActivity.class);
        startActivity(intent);
    }
}
