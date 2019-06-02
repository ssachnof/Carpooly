package com.example.carpooly.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:

                            case R.id.action_myRides:

                            case R.id.action_account:
                                openAccountPage();

                        }
                        return true;
                    }
                });
    }

    public void openAccountPage(){
        Intent intent = new Intent(this, AccountControl.class);
        startActivity(intent);
    }
}
