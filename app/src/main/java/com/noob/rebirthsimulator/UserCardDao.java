package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.UserCard;

import java.util.List;

@Dao
public interface UserCardDao {
    //获得所有数据库内容
    @Query("SELECT * FROM UserCard")
    List<UserCard> getAll();
    //用用户名查找卡片，返回卡片信息
    @Query("SELECT * FROM UserCard WHERE username LIKE :userNAME LIMIT 1")
    List<UserCard> findByName(String userNAME);
    //插入新卡片
    @Insert
    void insertAll(Card... cards);
    //删除卡片
    @Delete
    void delete(Card card);
}