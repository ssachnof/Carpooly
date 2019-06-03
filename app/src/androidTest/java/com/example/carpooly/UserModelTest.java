package com.example.carpooly;

import android.support.test.InstrumentationRegistry;

import com.example.carpooly.Model.UserInfoModel;
import com.example.carpooly.Model.UserModel;
import com.example.carpooly.Model.Driver;
import java.lang.Object;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserModelTest {

    @Test
    public void getEmail() {
        //Driver user = new Driver(InstrumentationRegistry.getTargetContext());
        UserInfoModel user = new UserInfoModel();
        assertNull(user.getEmail());
    }

    @Test
    public void getPassword() {
        UserInfoModel user = new UserInfoModel();
        assertNull(user.getPassword());
    }

    @Test
    public void getUser() {
    }

    @Test
    public void getDatabase() {
    }

    @Test
    public void getAuth() {
    }
}