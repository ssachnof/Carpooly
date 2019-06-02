package com.example.carpooly;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search {

    //retursn an arraylist of documents that satisfy contrain--will have to have cases for this
//    public Task<QuerySnapshot> queryCollection();
    // searches for a specific instance of a class
    public Query queryCollection(String contraint);
}
