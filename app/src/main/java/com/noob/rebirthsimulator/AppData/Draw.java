package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Draw {
    //自动配置id
    @PrimaryKey(autoGenerate = true)
    public int drawid;
    //距离保底次数
    @ColumnInfo(name = "pole")
    public int pole;
    //抽到的卡片记录
    @ColumnInfo(name = "cardhistory")
    public int cardhistory;

}
