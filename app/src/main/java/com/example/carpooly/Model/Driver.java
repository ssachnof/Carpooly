package com.example.carpooly.Model;

import android.content.Context;

public class Driver extends UserInfoModel {
    private String driverName;
    private UsersSearch search;


    public Driver(Context context){
        super(context);
    }

    public String getDriverName(String UId){
        return search.getName(search.queryCollection(UId).get().getResult());
    }



}
