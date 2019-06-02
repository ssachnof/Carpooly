package com.example.carpooly;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search {

    //retursn an arraylist of documents that satisfy contrain--will have to have cases for this
    public List<Map<String, Object>> queryCollection();
    // searches for a specific instance of a class
    public Map<String, Object> queryCollection(String contraint);
}
