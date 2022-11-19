package com.noob.rebirthsimulator.AppData;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.DrawDao;
import com.noob.rebirthsimulator.RebithTextDao;
import com.noob.rebirthsimulator.UserDao;

@Database(entities = {User.class,Card.class,Draw.class,RebirthText.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //获得Dao
    public abstract UserDao userDao();
    public abstract CardDao cardDao();
    public abstract DrawDao drawDao();
    public abstract RebithTextDao rebithTextDao();
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