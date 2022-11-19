package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RebirthText {
    //自动分配id
    @PrimaryKey(autoGenerate = true)
    public int ReTextid;
    //转生内容
    @ColumnInfo(name = "context")
    public  int context;
    //特殊文本
    @ColumnInfo(name = "specialtext")
    public int specialtext;

}