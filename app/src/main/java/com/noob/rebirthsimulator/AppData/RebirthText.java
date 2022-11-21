package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RebirthText {
    //自动分配id
    @PrimaryKey(autoGenerate = true)
    public int ReTextid;

    //内容
    @ColumnInfo(name = "context")
    public  String  context;

    //特殊文本
    @ColumnInfo(name = "specialtext")
    public String specialtext;

    //年龄
    @ColumnInfo(name = "age")
    public  int age;
}