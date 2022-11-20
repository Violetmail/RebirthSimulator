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
    public String cardStar;
    //卡片智力
    @ColumnInfo(name = "cardIg")
    public String cardIg;
    //卡片体力
    @ColumnInfo(name = "cardPhy")
    public String cardPhy;
    //卡片财富
    @ColumnInfo(name = "cardUg")
    public String cardUg;

}
