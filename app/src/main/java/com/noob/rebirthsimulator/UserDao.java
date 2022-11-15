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
    @Query("SELECT * FROM user")
    List<User> getall();

    @Query("SELECT water FROM user WHERE uid IN (:userIds)")
    int getwater(int userIds);


    @Query("SELECT * FROM user WHERE username LIKE :username LIMIT 1")
    User findByName(String username);

    @Update
    public void update(User... users);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
