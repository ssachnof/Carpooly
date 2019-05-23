package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class LoginUserActivity extends AppCompatActivity{
    private static final String CREDENTIALS = "credentials";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void authenticateUser(View view) throws IOException {
        Intent intent = new Intent(this, DisplayLoginMessage.class);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        LoginModel model = new LoginModel(username.getText().toString(),
                                          password.getText().toString());
        boolean auth_result = model.isValidLoginCredentials(this);
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
    public static String getKey(){return CREDENTIALS;}
    public void openRegPage(View view){
        Intent intent = new Intent(this, OpenRegPage.class);
        startActivity(intent);


    }
}
