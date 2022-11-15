package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Draw {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "drawid")
    public  int drawid;

    @ColumnInfo(name = "pole")
    public int pole;

    @ColumnInfo(name = "cardname")
    public int water;

}
