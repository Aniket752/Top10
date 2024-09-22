package com.aniket.top10.api.apiImplementation;

import com.aniket.top10.BuildConfig;
import com.aniket.top10.api.TopNewsApi;
import com.aniket.top10.common.ApiCallBack;
import com.aniket.top10.common.ApiResponse;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopNewsApiImplementation {
    private final TopNewsApi topNewsApi;
    public TopNewsApiImplementation(TopNewsApi topNewsApi){
        this.topNewsApi = topNewsApi;
    }

    public Disposable getTopNews(ApiCallBack<ApiResponse> callBack){
        Single<ApiResponse> single = topNewsApi.getMostViewedArticles(BuildConfig.api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return single.subscribe(arrayListApiResponse ->
                    callBack.onSuccess(arrayListApiResponse),
                throwable -> {
                    callBack.onError(throwable.getMessage());
        });
    }
}
