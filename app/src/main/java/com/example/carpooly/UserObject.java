package com.example.carpooly;

public class UserObject {
    private String email;
    private String name;
    private String phoneNumber;
    public UserObject(String email, String firstname, String lastname, String phoneNumber){
        this.email = email;
        this.name = firstname + " " + lastname;
        this.phoneNumber = phoneNumber;
    }

    //todo: make sure to add validate methods here!!!!!
}
