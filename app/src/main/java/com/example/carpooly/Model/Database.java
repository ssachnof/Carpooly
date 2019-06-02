package com.example.carpooly.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class Database {
    private static FirebaseFirestore singletonDataBase;
    private static FirebaseStorage singletonStorage;
    private static FirebaseAuth singletonAuth;



    public static FirebaseFirestore getDBInstance(){
        if (singletonDataBase == null) {
            singletonDataBase = FirebaseFirestore.getInstance();
        }
        return singletonDataBase;
    }
    public static FirebaseStorage getStorageInstance(){
        if(singletonStorage == null){
            singletonStorage = FirebaseStorage.getInstance();
        }
        return singletonStorage;
    }
    public static FirebaseAuth getAuthInstance(){
        if(singletonAuth == null){
            singletonAuth = FirebaseAuth.getInstance();
        }
        return singletonAuth;
    }
}
