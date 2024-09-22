package com.aniket.top10.news.viewModel;

import androidx.lifecycle.MutableLiveData;
import com.aniket.top10.base.BaseViewModel;
import com.aniket.top10.common.ApiCallBack;
import com.aniket.top10.common.ApiResponse;
import com.aniket.top10.database.topNews.TopNewsEntity;
import com.aniket.top10.news.repo.TopNewsRepo;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopNewsViewModel extends BaseViewModel {


    TopNewsRepo topNewsRepo = new TopNewsRepo();
    public MutableLiveData<List<TopNewsEntity>> topNewsList = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> error = new MutableLiveData<>();

    public TopNewsViewModel(){
        component.injectTopNewsViewModel(this);
    }

    public void getTopArticles(){
         Disposable disposable = topNewsRepo.getArticleData(new ApiCallBack<ApiResponse>() {
                     @Override
                     public void onSuccess(ApiResponse result) {
                         if(!result.results.isEmpty()){
                             topNewsRepo.insertArticleData(result.results);
                         } else {
                             error.setValue("Something went wrong");
                         }
                     }

                     @Override
                     public void onError(String msg) {
                         error.setValue(msg);
                     }
                 })
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(topNewsEntities -> topNewsList.setValue(topNewsEntities),
                         throwable ->error.setValue(throwable.getMessage()));
         compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        topNewsRepo.dispose();
        compositeDisposable.clear();
    }
}
