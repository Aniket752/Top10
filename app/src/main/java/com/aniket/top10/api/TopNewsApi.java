package com.aniket.top10.api;

import com.aniket.top10.common.ApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopNewsApi {

    @GET(APIUrls.MOST_VIEWED_ARTICLES)
    Single<ApiResponse> getMostViewedArticles(@Query("api-key") String key);

}
