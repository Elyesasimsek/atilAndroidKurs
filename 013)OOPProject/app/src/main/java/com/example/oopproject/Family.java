package com.example.oopproject;

public class Family {
    private String name;
    private int age;

    public Family(String name, int age) {
        this.name = name;
        this.age = age;
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

    public void setAge(int age, String password) {
        if (password.matches("elyesa"));{
            this.age = age;
        }
    }
}
