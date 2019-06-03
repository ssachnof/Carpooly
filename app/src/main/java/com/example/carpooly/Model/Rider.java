package com.example.carpooly.Model;

import android.content.Context;

public class Rider extends UserInfoModel {
    private UsersSearch search;
    public Rider(Context context){
        super(context);
        this.search = new UsersSearch(context);
    }

    public String getRiderName(String UId){
        return search.getName(search.queryCollection(UId).get().getResult());
    }

}
