package com.aniket.top10.database.topNews;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.aniket.top10.models.MediaModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "top_news")
public class TopNewsEntity {
    @PrimaryKey
    public Long id;
    public String Url;
    public String title;
    public String description;
    public String media;
}
