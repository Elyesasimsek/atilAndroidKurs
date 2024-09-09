package com.example.animebook;

import java.io.Serializable;

public class Anime implements Serializable {
    private int id;
    private String name;

    public Anime() {
    }

    public Anime(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
