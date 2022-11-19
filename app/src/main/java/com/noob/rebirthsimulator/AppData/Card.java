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
    //卡片属性，待补充
    @ColumnInfo(name = "cardAttribute")
    public String cardAttribute;

}
