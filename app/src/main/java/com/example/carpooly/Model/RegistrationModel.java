package com.example.carpooly.Model;

import android.content.Context;
import android.os.Environment;
import android.util.JsonWriter;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;*/

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.xml.parsers.FactoryConfigurationError;

public class RegistrationModel {
    private String email;
    private String pass;
    private String confirm_pass;
    private Context registrationContext;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public RegistrationModel(String email, String pass, String confirm_pass,
                             Context registrationContext) {
        this.email = email;
        this.pass = pass;
        this.confirm_pass = confirm_pass;
        this.registrationContext = registrationContext;
        this.auth = FirebaseAuth.getInstance();
        //this.user = auth.getCurrentUser();
    }

    //    public boolean createUser(Context context) throws IOException {
//        String lines = "";
//        JsonParser parser = new JsonParser();
//        try {
//            if(!pass.equals(confirm_pass))
//                throw new IllegalArgumentException("Passwords must match");
//            InputStream fs = context.openFileInput("users.json");
//            int max_size = fs.available();
//
//            //read the input file byte by byte
//            for (int i = 0; i < max_size; i++) {
//                lines += (char) fs.read();
//            }
//            JsonObject jo = (JsonObject) parser.parse(lines);
//            jo.getAsJsonObject("users").add(username, parser.parse("{password: " + pass + "}"));
//            String filename = "users.json";
//            FileOutputStream outputStream;
//            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(jo.toString().getBytes());
//            outputStream.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            try {
//                FileOutputStream outputStream;
//                JsonObject jo = new JsonObject();
//                jo.add("users", parser.parse("{" + username + ":" + " {password: " + pass + "}}"));
//                System.out.println(jo.toString());
//                outputStream = context.openFileOutput("users.json", Context.MODE_PRIVATE);
//                outputStream.write(jo.toString().getBytes());
//                outputStream.close();
//                return true;
//            } catch (Exception e1) {
//                e1.printStackTrace();
//                return false;
//            }
//        }
//        catch(IllegalArgumentException e2){
//            return false;
//        }
//        }
    public Task<AuthResult> registerUser() {
        return auth.createUserWithEmailAndPassword(email, pass);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return pass;
    }

    public void setAuth() {
        this.auth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getUser() {
        this.user = auth.getCurrentUser();
        return user;
    }
}