package com.aniket.top10.base;

import com.aniket.top10.common.ApiCallBack;
import com.aniket.top10.common.ApiResponse;
import com.aniket.top10.database.topNews.TopNewsEntity;
import com.aniket.top10.models.TopViewedArticleModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public interface ITopNewsRepo {

     void getTopArticles(ApiCallBack<ApiResponse> callBack);

     void insertArticleData(ArrayList<TopViewedArticleModel> list);

     void deleteAllArticles();

     Flowable<List<TopNewsEntity>> getArticleData(ApiCallBack<ApiResponse> callBack);

     void dispose();

}
