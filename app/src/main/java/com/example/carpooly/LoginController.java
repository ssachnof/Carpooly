package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;

public class LoginController extends AppCompatActivity{
    public static final String USERNAME_MESSAGE = "username";
    public static final String PASSWORD_MESSAGE = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, LoginView.class);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        String username_message = username.getText().toString();
        String password_message = password.getText().toString();
        try{
            boolean foundUser = LoginModel.isValidLoginCredentials(username_message, password_message, this);
            if (foundUser)
                intent.putExtra(USERNAME_MESSAGE, "success!!!");
            else
                intent.putExtra(USERNAME_MESSAGE, "failure!!!!");
        }
        catch (Exception e) {
            intent.putExtra(USERNAME_MESSAGE, "exception!!!!");
        }
        startActivity(intent);
    }

    //transition to registration page
    public void openRegPage(View view){
        setContentView(R.layout.activity_registration);
    }
}
