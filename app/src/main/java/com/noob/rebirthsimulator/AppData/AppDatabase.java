package com.noob.rebirthsimulator.AppData;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.RebithTextDao;
import com.noob.rebirthsimulator.TalentDao;
import com.noob.rebirthsimulator.UserCardDao;
import com.noob.rebirthsimulator.UserDao;

@Database(entities = {User.class,Card.class,UserCard.class,RebirthText.class,Talent.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //获得Dao
    public abstract UserDao userDao();
    public abstract CardDao cardDao();
    public abstract UserCardDao userCardDao();
    public abstract RebithTextDao rebithTextDao();
    public abstract TalentDao talentDao();
    //singleton(使得只能实例化一个对象)
    private static volatile AppDatabase INSTANCE;
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "App_db").allowMainThreadQueries().build();
                }
            }
        return INSTANCE;
    }



}