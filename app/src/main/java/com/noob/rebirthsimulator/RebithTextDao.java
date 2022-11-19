package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.Draw;
import com.noob.rebirthsimulator.AppData.RebirthText;

import java.util.List;

@Dao
public interface RebithTextDao {
    //获得RebithTextDao表内全部信息
    @Query("SELECT * FROM RebirthText")
    List<Draw> getAll();
//插入
    @Insert
    void insertAll(RebirthText...rebirthText );
//删除
    @Delete
    void delete(RebirthText rebirthText);
}