package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.Card;

import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * FROM card")
    List<Card> getAll();

    @Query("SELECT * FROM card WHERE cardid IN (:cardIds)")
    List<Card> loadAllByIds(int[] cardIds);

    @Query("SELECT * FROM card WHERE cardname LIKE :cardname LIMIT 1")
    Card findByName(String cardname);

    @Insert
    void insertAll(Card... cards);

    @Delete
    void delete(Card card);
}
