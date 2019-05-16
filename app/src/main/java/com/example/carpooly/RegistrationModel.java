package com.example.carpooly;

import android.content.Context;
import android.os.Environment;
import android.util.JsonWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

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
import java.util.LinkedHashMap;
import java.util.Scanner;
public class RegistrationModel {
    public boolean createUser(String username, String pass, Context context) throws IOException {
        System.out.println("username: " + username);
        System.out.println("pass: " + pass);
        String lines = "";
        JsonParser parser = new JsonParser();
        try {
            InputStream fs = context.openFileInput("users.json");
            int max_size = fs.available();

            //read the input file byte by byte
            for (int i = 0; i < max_size; i++) {
                lines += (char) fs.read();
            }
            JsonObject jo = (JsonObject) parser.parse(lines);
            System.out.print(lines);
            JsonObject tmp = new JsonObject();
            tmp.add("password", new JsonPrimitive("blah"));
            JsonObject tmp1 = new JsonObject();
            tmp1.addProperty("password", "blah");
            jo.getAsJsonObject("users").add(username, parser.parse("{password: " + pass + "}"));
            System.out.println(jo.toString());
            String filename = "users.json";
            FileOutputStream outputStream;
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(jo.toString().getBytes());
            outputStream.close();
            return true;
//            FileInputStream fis = context.openFileInput("temp.json");
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line);
//            }
//            System.out.println("line: "+ sb.toString());
//
//            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
