package com.aniket.top10.dagger.components;

import com.aniket.top10.base.BaseViewModel;
import com.aniket.top10.dagger.module.NetworkModule;
import com.aniket.top10.news.viewModel.TopNewsViewModel;

import dagger.Component;

@Component(dependencies = MyApplicationComponent.class,modules = {NetworkModule.class})
public interface ViewModelComponent {

    void injectBaseViewModel(BaseViewModel baseViewModel);
    void injectTopNewsViewModel(TopNewsViewModel topNewsViewModel);
}
