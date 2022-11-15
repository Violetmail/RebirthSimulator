package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.Draw;

import java.util.List;

@Dao
public interface DrawDao {
    @Query("SELECT * FROM Draw")
    List<Draw> getAll();

    @Insert
    void insertAll(Draw... cards);

    @Delete
    void delete(Draw card);
}