package com.aniket.top10.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.aniket.top10.database.topNews.TopNewsDao;
import com.aniket.top10.database.topNews.TopNewsEntity;

@Database(entities = {TopNewsEntity.class},version = 1,exportSchema = false)
abstract public class AppDatabase extends RoomDatabase {

    abstract public TopNewsDao topNewsDto();

    synchronized public static AppDatabase getInstance(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Top10")
                .build();
    }
}
