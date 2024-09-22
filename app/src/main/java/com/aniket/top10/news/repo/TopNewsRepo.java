package com.aniket.top10.news.repo;

import android.util.Log;

import com.aniket.top10.api.apiImplementation.TopNewsApiImplementation;
import com.aniket.top10.base.BaseRepo;
import com.aniket.top10.common.ApiCallBack;
import com.aniket.top10.common.ApiResponse;
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


public class TopNewsRepo extends BaseRepo {
    private final String TAG = "TopNewsRepo";
    @Inject
    TopNewsApiImplementation topNewsApiImplementation;
    @Inject
    AppDatabase database;

    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public TopNewsRepo(){
        component.injectTopNewsRepo(this);
    }

    public void getTopArticles(){
        Disposable disposable = topNewsApiImplementation.getTopNews(new ApiCallBack<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if(!result.results.isEmpty()){
                    insertArticleData(result.results);
                } else {
                    System.out.println("Something went wrong");
                }
            }

            @Override
            public void onFailure(int statusCode) {
                System.out.println(statusCode);
            }
        });
        compositeDisposable.add(disposable);
    }

    public void insertArticleData(ArrayList<TopViewedArticleModel> list){
        deleteAllArticles();
        for (int i =0 ; i< list.size(); i++){
            TopNewsEntity entity = list.get(i).toTopNewsEntity();
            database.topNewsDto().insertTopNews(entity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(TAG,"Added")).subscribe();
        }

    }

    public void deleteAllArticles(){
        database.topNewsDto().deleteAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(TAG,"Deleted") ).subscribe();
    }

    public Flowable<List<TopNewsEntity>> getArticleData(){
        getTopArticles();
        return database.topNewsDto().getTopNews();
    }

    public void dispose(){
        compositeDisposable.clear();
    }


}
