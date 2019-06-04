package com.example.carpooly.Model;

import android.content.Context;
import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginModelTest {

    @Test
    public void signInUser() {
    }

    @Test
    public void write() {
    }

    @Test
    public void read() {
    }

    @Test
    public void singletonTest(){
        LoginModel l = LoginModel.getInstance("mammoth11@comcast.net", "test1234", null);
        //LoginModel l2 = LoginModel.getInstance();
        LoginModel l3 = LoginModel.getInstance("mammoth11@comcast.net", "test1234", null);

        assertNotNull(l);
        assertNotNull(l3);
        assertSame(l, l3);

    }
}