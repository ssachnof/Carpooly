package com.example.carpooly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class OpenRegPage extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirm_password;
    private RegistrationModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        this.confirm_password = (EditText) findViewById(R.id.confirmPassword);
        this.model = new RegistrationModel();
    }

    public void displayRegMessage(View view) throws IOException {
        Intent intent = new Intent(this, DisplayLoginMessage.class);
        intent.putExtra(LoginUserActivity.CREDENTIALS, "user successfully registered!!!!");
        boolean result = model.createUser(username.getText().toString(),
                password.getText().toString(), this);
        startActivity(intent);

    }
}
