package com.aniket.top10.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MediaModel {
    public String type;
    public String subType;
    @SerializedName("media-metadata")
    public ArrayList<MediaMetaDataModel> imageData;
}
