package com.example.carpooly.Model;

import android.content.Context;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class Rider extends RideModel {
    private UsersSearch search;
    String riderName;

    public Rider(Context context, String name, String UId) {
        super(context);
        this.search = new UsersSearch(context);
    }

    public Query getRiderInfo() {
        System.out.println(super.getUId());
        return super.queryCollection(super.getUId());
    }

    public void setName(QuerySnapshot queryDocumentSnapshots) {
        this.riderName = super.getName(queryDocumentSnapshots);
    }

    private void setRiderName(String name){
        this.riderName = name;
        System.out.println("set name: " + riderName);
    }


    public void getRiderName(DocumentReference docRef) {
        final String[] rName = new String[1];
        getRiderInfo().addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                System.out.println("RIdER name: " + (String)queryDocumentSnapshots.getDocuments().get(0).get("Name"));
                assert queryDocumentSnapshots != null;
                setRiderName((String)queryDocumentSnapshots.getDocuments().get(0).get("Name"));
                docRef.update("Riders", FieldValue.arrayUnion(riderName));

            }
        });
            //TODO: THIS IS WHERE OUR COUPLING IS OCCURING!!!!!!!
        System.out.println(this.riderName);//todo: THIS SHOULD NOT BE NULL!!!!!!!!!
    }
}
