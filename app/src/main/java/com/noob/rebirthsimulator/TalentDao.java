package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.Talent;

import java.util.List;

@Dao
public interface TalentDao {
    //获得全部对象
    @Query("SELECT * FROM talent")
    List<Talent> getall();

    //插入天赋
    @Insert
    void insertAll(Talent...talents);
    //删除天赋
    @Delete
    void delete(List<Talent> talents);

}
