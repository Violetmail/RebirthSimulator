package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "water")
    public int water;

    @ColumnInfo(name = "fragment")
    public int fragment;

}
