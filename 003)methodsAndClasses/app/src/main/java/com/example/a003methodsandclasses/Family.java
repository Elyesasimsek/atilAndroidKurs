package com.example.a003methodsandclasses;

public class Family {
    private String name;
    private int age;

    public Family() {
    }

    public Family(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("constructor called");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
