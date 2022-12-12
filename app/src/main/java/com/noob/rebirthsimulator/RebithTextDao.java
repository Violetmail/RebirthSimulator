package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.noob.rebirthsimulator.AppData.RebirthText;

import java.util.List;

@Dao
public interface RebithTextDao {
    //获得RebithTextDao表内全部信息
    @Query("SELECT * FROM RebirthText")
    List<RebirthText> getAll();

    //获取某个年龄的全部对象
    @Query("SELECT * FROM rebirthtext WHERE age IN (:AGE)")
    List<RebirthText> fingByAge(int AGE);

    //获取特定要求的对象
    @Query("SELECT * FROM rebirthtext WHERE age= (:AGE) and NeedAp>=(:ap) and NeedIg>=(:ig) and NeedPhy>=(:phy) and NeedUg>=(:ug)" )
    List<RebirthText> fingByCondition(int AGE,int ap,int ig,int phy,int ug);

//插入
    @Insert
    void insertAll(RebirthText...rebirthText );

//删除
    @Delete
    void delete(RebirthText rebirthText);
}