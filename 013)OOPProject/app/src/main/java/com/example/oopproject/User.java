package com.example.oopproject;

public class User extends People{

    //property
    String name;
    String job;

    //constructor


    public User(String name, String job) {
        this.name = name;
        this.job = job;
        System.out.println("User Created");
    }
}
