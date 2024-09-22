package com.aniket.top10.api.apiImplementation;

import com.aniket.top10.api.TopNewsApi;
import com.aniket.top10.common.ApiCallBack;
import com.aniket.top10.common.ApiResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TopNewsApiImplementation {
    private final TopNewsApi topNewsApi;
    public TopNewsApiImplementation(TopNewsApi topNewsApi){
        this.topNewsApi = topNewsApi;
    }

    public Disposable getTopNews(ApiCallBack<ApiResponse> callBack){
        Single<ApiResponse> single = topNewsApi.getMostViewedArticles("3K5LWpB6jRfcSFsLGkttEsifFb0SxVK2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return single.subscribe(new Consumer<ApiResponse>() {
            @Override
            public void accept(ApiResponse arrayListApiResponse) throws Exception {
                callBack.onSuccess(arrayListApiResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(throwable);
//                callBack.onFailure(throwable.getMessage());
            }
        });
    }
}
