package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RebirthText {
    //自动分配id
    @PrimaryKey(autoGenerate = true)
    public int ReTextid;
    //年龄
    @ColumnInfo(name = "age")
    public  int age;
    //转生内容
    @ColumnInfo(name = "context")
    public  String context;
    //分支一
    @ColumnInfo(name = "branch1text")
    public String branch1text;
    //分支二
    @ColumnInfo(name = "branch2text")
    public String branch2text;

}