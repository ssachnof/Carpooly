package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayLoginMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginUserActivity.CREDENTIALS);

        //display the username
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText(username);

        //display the password
    }
}
