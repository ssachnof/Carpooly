package com.example.carpooly;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface viewUpdater {
    public void updateUI(FirebaseUser user);
    public Intent getNextIntent();
}
