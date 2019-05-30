package com.example.carpooly.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpooly.Model.EditTextExtractor;
import com.example.carpooly.ElementExtractor;
import com.example.carpooly.R;
import com.example.carpooly.Model.UserInfoModel;
import com.example.carpooly.viewUpdater;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class OpenRegPageController extends AppCompatActivity implements viewUpdater {
    private UserInfoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void displayRegMessage(View view) throws IOException {
        ElementExtractor extractor = new EditTextExtractor();
        // extract fields
        String email = extractor.extractElement(findViewById(R.id.Email));
        String password = extractor.extractElement(findViewById(R.id.password));
        String confirmPassword = extractor.extractElement(findViewById(R.id.confirmPassword));
        String phoneNumber = extractor.extractElement(findViewById(R.id.PhoneNumber));
        String firstName = extractor.extractElement(findViewById(R.id.FirstName));
        String lastName = extractor.extractElement(findViewById(R.id.LastName));
        this.model = new UserInfoModel(email, password, confirmPassword, phoneNumber, firstName, lastName, this);
        if (!validatePassword(password, confirmPassword)){
            updateUI(null);
        }
        else {
            this.model = new UserInfoModel(email, password, confirmPassword, phoneNumber,
                                                firstName, lastName, this);
            //model.setAuth();
            Task<AuthResult> regTask = model.registerUser();
            regTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = model.getUser();
                        model.write();
                        updateUI(user);
                    } else {
                        updateUI(null);
                    }
                }
            });
        }

    }

    private boolean validatePassword(String password, String confirmPass){
        return password.equals(confirmPass);
    }

    public void updateUI(FirebaseUser user){
        if (user != null){
            Intent intent = getNextIntent();
            Toast toast = Toast.makeText(OpenRegPageController.this, "Registration Successful",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(OpenRegPageController.this, "Registration Failure",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            ((EditText) findViewById(R.id.Email)).setText("Email");
            ((EditText) findViewById(R.id.password)).setText("Password");
            ((EditText) findViewById(R.id.confirmPassword)).setText("Password");
        }
    }


    public Intent getNextIntent(){
        return new Intent(this, LoginUserActivity.class);
    }
}
