package com.jewong.calcac.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jewong.calcac.data.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) sInstance = getNewInstance(context);
        return sInstance;
    }

    private static AppDatabase getNewInstance(Context context) {
        synchronized (new Object()) {
            return Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "AppDatabase").build();
        }
    }

    public abstract UserDao userDao();

}

