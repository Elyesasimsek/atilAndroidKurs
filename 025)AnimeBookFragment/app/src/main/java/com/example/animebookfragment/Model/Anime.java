package com.example.animebookfragment.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Anime implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NotNull
    private int id;
    @ColumnInfo(name = "name")
    @NotNull
    private String name;
    @ColumnInfo(name = "year")
    @NotNull
    private String year;
    @ColumnInfo(name = "imdb")
    @NotNull
    private String imdb;
    @ColumnInfo(name = "image")
    @NotNull
    private byte[] image;

    public Anime() {
    }

    public Anime(@NotNull String name, @NotNull String year, @NotNull String imdb, @NotNull byte[] image) {
        this.name = name;
        this.year = year;
        this.imdb = imdb;
        this.image = image;
    }

    public Anime(int id, @NotNull String name, @NotNull String year, @NotNull String imdb, @NotNull byte[] image) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.imdb = imdb;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getYear() {
        return year;
    }

    public void setYear(@NotNull String year) {
        this.year = year;
    }

    @NotNull
    public String getImdb() {
        return imdb;
    }

    public void setImdb(@NotNull String imdb) {
        this.imdb = imdb;
    }

    @NotNull
    public byte[] getImage() {
        return image;
    }

    public void setImage(@NotNull byte[] image) {
        this.image = image;
    }
}
