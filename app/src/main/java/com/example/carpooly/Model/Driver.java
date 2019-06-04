package com.example.carpooly.Model;

import android.content.Context;

import java.util.Map;

public class Driver extends UsersSearch {
    private String driverName;
    private UsersSearch search;
    private UserInfoModel driverInfo;

    public Driver(Context context, String UId){
        super(context, UId);
        this.driverInfo = super.getUserInfo();
    }


    public UserInfoModel getDriverInfo(){
        return super.getUserInfo();
    }

    public String getDriverId(){
        assert(driverInfo != null);
        return driverInfo.getUId();
    }



}
