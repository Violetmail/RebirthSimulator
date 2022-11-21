package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {
    //自动给予主键id
    @PrimaryKey(autoGenerate = true)
    public int cardid;

    //卡片名称
    @ColumnInfo(name = "cardname")
    public String cardname;

    //卡片星级
    @ColumnInfo(name = "cardStar")
    public int cardStar;

    //卡片智力
    @ColumnInfo(name = "cardIg")
    public int cardIg;

    //卡片外貌
    @ColumnInfo(name = "cardAp")
    public int cardAp;

    //卡片体力
    @ColumnInfo(name = "cardPhy")
    public int cardPhy;

    //卡片财富
    @ColumnInfo(name = "cardUg")
    public int cardUg;

    //卡片价值
    @ColumnInfo(name = "cardvalue")
    public int cardvalue;

}
