package com.example.animebookfragment.RoomDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.animebookfragment.Model.Anime;

@Database(entities = {Anime.class}, version = 1)
public abstract class AnimeDatabase extends RoomDatabase {
    public abstract AnimeDAO animeDAO();
}
