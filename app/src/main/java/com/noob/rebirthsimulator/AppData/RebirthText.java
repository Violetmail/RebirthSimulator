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

    //内容
    @ColumnInfo(name = "context")
    public  String  context;

    //角色
    @ColumnInfo(name ="character")
    public  String character;

    //分支文本一
    @ColumnInfo(name = "branchtext1")
    public String branchtext1;

    //分支文本二
    @ColumnInfo(name = "branchtext2")
    public String branchtext2;

    //智力需求
    @ColumnInfo(name = "NeedIg")
    public  int NeddIg;

    //外貌需求
    @ColumnInfo(name = "NeedAp")
    public  int NeddAp;

    //体力需求
    @ColumnInfo(name = "NeedPhy")
    public  int NeddPhy;

    //财富需求
    @ColumnInfo(name = "NeedUg")
    public  int NeddUg;


}