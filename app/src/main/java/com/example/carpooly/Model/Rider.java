package com.example.carpooly.Model;

import android.content.Context;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class Rider extends UsersSearch {
    private UsersSearch search;
    String riderName;
    private String UId;
    private Context context;
    private UserInfoModel riderInfo;

    public Rider(Context context, String UId) {
        super(context, UId);
        this.context = context;
        this.riderInfo = super.getUserInfo();
    }

    public UserInfoModel getRiderInfo(){
        return super.getUserInfo();
    }

    public String getRiderId(){
        return riderInfo.getUId();
    }
}
