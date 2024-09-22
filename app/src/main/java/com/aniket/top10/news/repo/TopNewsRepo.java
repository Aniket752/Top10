package com.aniket.top10.news.repo;

import android.util.Log;

import com.aniket.top10.MyApplication;
import com.aniket.top10.api.apiImplementation.TopNewsApiImplementation;
import com.aniket.top10.base.ITopNewsRepo;
import com.aniket.top10.common.ApiCallBack;
import com.aniket.top10.common.ApiResponse;
import com.aniket.top10.dagger.components.DaggerRepoComponent;
import com.aniket.top10.dagger.components.RepoComponent;
import com.aniket.top10.database.AppDatabase;
import com.aniket.top10.database.topNews.TopNewsEntity;
import com.aniket.top10.models.TopViewedArticleModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TopNewsRepo implements ITopNewsRepo {
    private final String TAG = "TopNewsRepo";
    @Inject
    TopNewsApiImplementation topNewsApiImplementation;
    @Inject
    AppDatabase database;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected RepoComponent component;

    public TopNewsRepo() {

        component = DaggerRepoComponent.builder()
                .myApplicationComponent(MyApplication.getComponent())
                .build();
        component.injectTopNewsRepo(this);
    }

    @Override
    public void getTopArticles(ApiCallBack<ApiResponse> callBack) {
        Disposable disposable = topNewsApiImplementation.getTopNews(callBack);
        compositeDisposable.add(disposable);
    }

    @Override
    public void insertArticleData(ArrayList<TopViewedArticleModel> list) {
        deleteAllArticles();
        for (int i = 0; i < list.size(); i++) {
            TopNewsEntity entity = list.get(i).toTopNewsEntity();
            database.topNewsDto().insertTopNews(entity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(TAG, "Added")).subscribe();
        }

    }

    @Override
    public void deleteAllArticles() {
        database.topNewsDto().deleteAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(TAG, "Deleted")).subscribe();
    }

    @Override
    public Flowable<List<TopNewsEntity>> getArticleData(ApiCallBack<ApiResponse> callBack) {
        getTopArticles(callBack);
        return database.topNewsDto().getTopNews();
    }

    @Override
    public void dispose() {
        compositeDisposable.clear();

    }


}
