package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.RebirthText;
import com.noob.rebirthsimulator.AppData.UserCard;

import java.util.List;

@Dao
public interface UserCardDao {
    //获得UserCardtDao表内全部信息
    @Query("SELECT * FROM UserCard")
    List<UserCard> getAll();

    //
    @Query("SELECT cardname FROM UserCard WHERE username IN(:userNAME) ")
    List<String> getAllcardname(String userNAME);

    //用名称查找卡片，返回用户卡牌对象
    @Query("SELECT * FROM UserCard WHERE username IN(:userNAME)")
    List<UserCard> findByName(String userNAME);

    //插入
    @Insert
    void insertAll(UserCard... userCards);

    //删除
    @Delete
    void delete(UserCard userCard);
}