package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class OpenRegPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void displayRegMessage(View view) throws IOException {
        Intent intent = new Intent(this, DisplayLoginMessage.class);
        intent.putExtra(LoginUserActivity.CREDENTIALS, "user successfully registered!!!!");
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        String username_message = username.getText().toString();
        String password_message = password.getText().toString();
        boolean result = RegistrationModel.createUser(username_message, password_message, this);
        System.out.println("result: "+ result);
        startActivity(intent);

    }
}
