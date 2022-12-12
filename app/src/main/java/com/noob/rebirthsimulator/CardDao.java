package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.noob.rebirthsimulator.AppData.Card;

import java.util.List;

@Dao
public interface CardDao {
    //获得所有数据库内容
    @Query("SELECT * FROM card")
    List<Card> getAll();

    //获取所有卡片名称
    @Query("SELECT cardname FROM card")
    List<String> getAllcardname();


    //用卡片名称查找卡片，返回卡片信息
    @Query("SELECT * FROM card WHERE cardname IN(:cardname)")
    Card findByName(String cardname);
    //返回星级卡组
    @Query("SELECT * FROM card WHERE cardStar LIKE :star")
    List<Card> findByStar(int star);

    //更新表
    @Update
    void  updatecard(Card...cards);
    //插入新卡片
    @Insert
    void insertAll(Card... cards);
    //删除卡片
    @Delete
    void delete(List<Card> cards);
}
