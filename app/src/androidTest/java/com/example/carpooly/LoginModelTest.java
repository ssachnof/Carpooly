package com.example.carpooly;

import com.example.carpooly.Model.LoginModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginModelTest {

    @Test
    public void signInUser() {
    }

    @Test(expected= RuntimeException.class)
    public void checkLoginModelException() {
        LoginModel.getInstance();
    }
    
    @Test
    public void write() {
    }

    @Test
    public void read() {
    }
}