package com.aniket.top10.news.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.aniket.top10.MyApplication;
import com.aniket.top10.base.BaseViewModel;
import com.aniket.top10.dagger.components.DaggerViewModelComponent;
import com.aniket.top10.dagger.components.ViewModelComponent;
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

    public TopNewsViewModel(){
        component.injectTopNewsViewModel(this);
    }

    public void getTopArticles(){
         Disposable disposable = topNewsRepo.getArticleData()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(topNewsEntities -> topNewsList.setValue(topNewsEntities),
                         throwable -> System.out.println(throwable.getMessage()));
         compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        topNewsRepo.dispose();
        compositeDisposable.clear();
    }
}
