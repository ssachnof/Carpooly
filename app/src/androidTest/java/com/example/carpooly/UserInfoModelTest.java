package com.example.carpooly;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.carpooly.Model.UserInfoModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class UserInfoModelTest {
    @Test
    public void getName() {
        UserInfoModel userInfo = new UserInfoModel();
        userInfo.setName("John Doe");

        assertEquals("John Doe", userInfo.getName());

    }

    @Test
    public void setName() {
    }
}