package com.example.carpooly.Model;

import android.content.Context;

<<<<<<< HEAD
public class Driver extends UserInfoModel {
=======
public class Driver extends RideModel {
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
    private String driverName;
    private UsersSearch search;


    public Driver(Context context){
        super(context);
    }

    public String getDriverName(String UId){
        return search.getName(search.queryCollection(UId).get().getResult());
    }



<<<<<<< HEAD
}
=======
}
>>>>>>> bdd1cda8f9b7a9acdcc99039cd7be79a4ab1abba
