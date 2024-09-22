package com.aniket.top10.models;

import com.aniket.top10.database.topNews.TopNewsEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopViewedArticleModel {
    public Long id;
    public String url;
    public String title;
    @SerializedName("abstract")
    public String description;
    public ArrayList<MediaModel> media;

    public TopNewsEntity toTopNewsEntity(){
        TopNewsEntity entity = new TopNewsEntity();
        entity.id = id;
        entity.description = description;
        entity.Url = url;
        entity.title = title;
        if(!media.isEmpty()){
            if (!media.get(0).imageData.isEmpty()){
                entity.media = media.get(0).imageData.get(media.get(0).imageData.size() - 1).url;
            }
        }


        return entity;
    }

}
