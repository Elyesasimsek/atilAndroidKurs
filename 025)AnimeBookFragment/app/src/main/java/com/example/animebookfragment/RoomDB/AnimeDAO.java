package com.example.animebookfragment.RoomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.animebookfragment.Model.Anime;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface AnimeDAO {
    @Query("SELECT * FROM Anime")
    Flowable<List<Anime>> getAll();

    @Query("SELECT * FROM Anime WHERE id = :id")
    Flowable<Anime> getArtById(int id);

    @Insert
    Completable insert(Anime anime);

    @Update
    Completable update(Anime anime);
}
