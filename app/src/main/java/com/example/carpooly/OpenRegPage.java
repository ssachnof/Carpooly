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
        // extract fields
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText confirm_password = (EditText) findViewById(R.id.confirmPassword);
        RegistrationModel model = new RegistrationModel(username.getText().toString(),
                                                        password.getText().toString());
        boolean result = model.createUser(this);
        intent.putExtra(LoginUserActivity.getKey(), "user successfully registered!!!!");
        startActivity(intent);

    }
}
