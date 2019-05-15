package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OpenRegPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void displayRegMessage(View view){
        Intent intent = new Intent(this, DisplayLoginMessage.class);
        intent.putExtra(LoginUserActivity.CREDENTIALS, "user successfully registered!!!!");
        startActivity(intent);

    }
}
