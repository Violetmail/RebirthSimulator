package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserCard {
    //自动给予主键id
    @PrimaryKey(autoGenerate = true)
    public int cardid;

    //用户名
    @ColumnInfo(name = "username")
    public String username;

    //卡片名称
    @ColumnInfo(name = "cardname")
    public String cardname;

    //卡片星级
    @ColumnInfo(name = "cardstar")
    public int cardstar;

    //卡片价值
    @ColumnInfo(name = "cardvalue")
    public int cardvalue;


}