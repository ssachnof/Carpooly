package com.example.carpooly.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpooly.Model.*;
import com.example.carpooly.Model.EditTextExtractor;
import com.example.carpooly.ElementExtractor;
import com.example.carpooly.R;
import com.example.carpooly.viewUpdater;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginUserActivity extends AppCompatActivity implements viewUpdater {
    private static final String EMAIL = "email";
    private int RC_SIGN_IN;
    private List<AuthUI.IdpConfig> providers;
    private ActionCodeSettings actionCodeSettings;
    private LoginModel model;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
        //todo: might be better to create an onclick listener here and create the model object
        //this.model.setAuth();
        //this.actionCodeSettings = enableEmailLinkSignIn();
        setContentView(R.layout.activity_main);
    }


    private ActionCodeSettings enableEmailLinkSignIn(){
        return ActionCodeSettings.newBuilder()
                .setAndroidPackageName(/* yourPackageName= */ "com.example.carpooly",
                        /* installIfNotAvailable= */ true,
                        /* minimumVersion= */ "16").setHandleCodeInApp(true) // This must be set to true
                .setUrl("https://google.com") // This URL needs to be whitelisted
                .build();
    }


    public void authenticateUser(View view) throws IOException {
        ElementExtractor extractor = new EditTextExtractor();
        View fieldItem = findViewById(R.id.Email);
        String email = extractor.extractElement(fieldItem);
        fieldItem  = findViewById(R.id.password);
        String password = extractor.extractElement(fieldItem);

        Intent intent = getIntent();

        this.model = new LoginModel(email, password, this);
        Task<AuthResult> signInTask = model.signInUser();
        signInTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    UserModel.user = model.getCurrentUser();
                    FirebaseUser user = model.getCurrentUser();
                    Log.d("LoginUserActivity.class", "signInWithEmail:success");
                    updateUI(user);

                }
                else{
                    System.out.println("UNSUCCESSFUL LOGIN!!!!!!");
                    Toast toast = Toast.makeText(LoginUserActivity.this, "Authentication failed.",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                    updateUI(null);
                }
            }
        });
    }

    public Intent getNextIntent(){ return new Intent(this, DisplayLoginMessage.class); }


    // note: this function will change when you add the home screen in
    // should eventually redirect to the home screen
    public void updateUI(FirebaseUser user){
        if (user != null) {
            Intent intent = getNextIntent();
            intent.putExtra(getUserEmailKey(), model.getEmail());
            startActivity(intent);
        }
        else{
            ((EditText) findViewById(R.id.Email)).setText("Email");
            ((EditText) findViewById(R.id.password)).setText("Password");
        }
    }

    public static String getUserEmailKey(){return EMAIL;}

    public void openRegPage(View view){
        Intent intent = new Intent(this, OpenRegPageController.class);
        startActivity(intent);


    }
}
