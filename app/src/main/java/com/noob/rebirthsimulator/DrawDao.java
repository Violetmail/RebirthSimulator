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
    //获得Draw表内全部信息
    @Query("SELECT * FROM Draw")
    List<Draw> getAll();
    //插入
    @Insert
    void insertAll(Draw... draws);
//删除
    @Delete
    void delete(Draw draw);
}