package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.Card;

import java.util.List;

@Dao
public interface CardDao {
    //获得所有数据库内容
    @Query("SELECT * FROM card")
    List<Card> getAll();

    //用卡片名称查找卡片，返回卡片信息
    @Query("SELECT * FROM card WHERE cardname LIKE :cardname LIMIT 1")
    Card findByName(String cardname);
    //插入新卡片
    @Insert
    void insertAll(Card... cards);
    //删除卡片
    @Delete
    void delete(Card card);
}
