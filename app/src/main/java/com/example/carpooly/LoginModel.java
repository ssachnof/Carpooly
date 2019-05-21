package com.example.carpooly;

import android.content.Context;
import android.widget.EditText;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;*/

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LoginModel {
    public static boolean isValidLoginCredentials(String username, String pass, Context context) throws IOException {
        /*String lines = "";
        JsonParser parser = new JsonParser();
        try {
            InputStream fs = context.openFileInput("users.json");
            int max_size = fs.available();

            //read the input file byte by byte
            for (int i = 0; i < max_size; i++) {
                lines += (char) fs.read();
            }

            JsonObject jo = (JsonObject) parser.parse(lines);
            String db_username = jo.getAsJsonObject("users").get(username).toString();
            String db_password = jo.getAsJsonObject("users").getAsJsonObject(username).get("password").getAsString();
            fs.close();
            // we can probably get rid of the first condition here, but to be on the safe side ...
            return (db_username != null) && (db_password.equals(pass));


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }*/
        return false;
    }
}