package com.example.carpooly.Model;

import android.content.Context;

import com.example.carpooly.Search;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsersSearch extends UserInfoModel implements Search {
    private final CollectionReference usersRef = super.getUsersCollection();
    private String driverName;
    public UsersSearch(Context context){
        super(context);
    }
    public UsersSearch(){}
    public List<Map<String, Object>> queryCollection() {
        List<DocumentSnapshot> userDocuments=  usersRef.get().getResult().getDocuments();
        ArrayList<Map<String, Object>> usersData = new ArrayList<>();

        for (DocumentSnapshot userDoc : userDocuments){
            usersData.add(userDoc.getData());
        }
        return usersData;
    }
    //searches for a specific user
    public Map<String, Object> queryCollection(String UId){
        Query findUserQuery = usersRef.whereEqualTo("UserId", UId);
        return findUserQuery.get().getResult().getDocuments().get(0).getData();
    }

    public String getDriverName(String UId){
        Map<String, Object> userData = queryCollection(UId);
        return (String)userData.get("Name");
    }
}
