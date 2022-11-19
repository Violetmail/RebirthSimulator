package com.noob.rebirthsimulator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.noob.rebirthsimulator.AppData.User;

import java.util.List;

@Dao
public interface UserDao {
    //获得user表内全部信息
    @Query("SELECT * FROM user")
    List<User> getAll();
//获得用户水晶数量
    @Query("SELECT * FROM user WHERE username IN (:userNAME)")
    User getwater(String userNAME);
//更新用户水晶数
    @Query("UPDATE User SET water=:newwater WHERE username IN (:userNAME)")
    void updatawater(String userNAME,int newwater);

//用用户名返回用户
    @Query("SELECT * FROM user WHERE username LIKE :username LIMIT 1")
    User findByName(String username);

    //改变登陆状态
    @Query("UPDATE User SET iflogin=:iflogin WHERE username in(:userNAME)")
    void updatelogin(boolean iflogin,String userNAME);

    //获得登陆or注销的用户
    @Query("SELECT * FROM user WHERE iflogin IN (:iflogin)")
    User getloginuser(boolean iflogin);

//更新user表
    @Update
    void  update(User...users);
//插入
    @Insert
    void insertAll(User... users);
//删除
    @Delete
    void delete(User user);
}