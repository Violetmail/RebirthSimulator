package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {
    @PrimaryKey(autoGenerate = true)
    public int cardid;

    @ColumnInfo(name = "cardname")
    public String cardname;

    @ColumnInfo(name = "cardAttribute")
    public String cardAttribute;

}
