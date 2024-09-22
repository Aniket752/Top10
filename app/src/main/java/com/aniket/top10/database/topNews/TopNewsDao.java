package com.aniket.top10.database.topNews;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TopNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTopNews(TopNewsEntity topNewsEntity);

    @Query("SELECT * FROM top_news")
    Flowable<List<TopNewsEntity>> getTopNews();

    @Query("DELETE FROM top_news")
    Completable deleteAllNews();
}
