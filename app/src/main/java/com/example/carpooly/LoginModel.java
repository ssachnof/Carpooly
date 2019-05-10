package com.example.carpooly;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class LoginModel {
    public static boolean isValidLoginCredentials(String username, String pass) throws IOException{
        System.out.println("RUNNING!!!!!!\n");
        String lines = "";
        try {
            Scanner sc = new Scanner(new File("/Users/samuelsachnoff/repos/Carpooly/app/src/main/java/com/example/carpooly/sample.txt"));
//            while (sc.hasNextLine()) {
//            String line = sc.nextLine();
//            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return username.equals("abcd");
    }
}