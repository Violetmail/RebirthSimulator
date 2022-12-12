package com.noob.rebirthsimulator.AppData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Talent {
    //自动给予主键id
    @PrimaryKey(autoGenerate = true)
    public int talentid;

    //天赋内容
    @ColumnInfo(name = "TalentContext")
    public String TalentContext;

    //操作的属性
    @ColumnInfo(name = "TalentProperty")
    public String TalentProperty;

    //改变数值
    @ColumnInfo(name = "modifyValue")
    public  int modifyValue;

}
