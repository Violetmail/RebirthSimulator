package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    //自动分配id
    @PrimaryKey(autoGenerate = true)
    public int uid;
    //用户名
    @ColumnInfo(name = "username")
    public String username;
    //用户水晶数量
    @ColumnInfo(name = "water")
    public int water;
    //用户碎片数量
    @ColumnInfo(name = "fragment")
    public int fragment;
    //用户保底次数
    @ColumnInfo(name = "drawcounter")
    public int drawcounter;
    //用户是否登陆
    @ColumnInfo(name = "iflogin")
    public boolean iflogin;

}
