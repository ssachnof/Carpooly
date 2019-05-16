package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginUserActivity extends AppCompatActivity{
    private LoginModel model;
    public static final String CREDENTIALS = "credentials";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText username = (EditText) findViewById(R.id.username);
        System.out.println("USERNAME: " + username.getText().toString());
        EditText pass = (EditText) findViewById(R.id.password);
        this.model = new LoginModel(username, pass);
    }

    //public static String getKey(){return CREDENTIALS;}
    public void authenticateUser(View view){
        Intent intent = new Intent(this, DisplayLoginMessage.class);
        try{
            boolean foundUser = model.isValidLoginCredentials(this);
            if (foundUser)
                intent.putExtra(CREDENTIALS, "success!!!");
            else
                intent.putExtra(CREDENTIALS, "failure!!!!");
        }
        catch (Exception e) {
            intent.putExtra(CREDENTIALS, "exception!!!!");
        }
        startActivity(intent);
    }
    public void openRegPage(View view){
        Intent intent = new Intent(this, OpenRegPage.class);
        startActivity(intent);


    }
}
