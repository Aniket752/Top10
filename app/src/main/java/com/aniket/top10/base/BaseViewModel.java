package com.aniket.top10.base;

import androidx.lifecycle.ViewModel;

import com.aniket.top10.MyApplication;
import com.aniket.top10.dagger.components.DaggerViewModelComponent;
import com.aniket.top10.dagger.components.ViewModelComponent;

public class BaseViewModel extends ViewModel {
    protected ViewModelComponent component;

    public BaseViewModel(){
        component = DaggerViewModelComponent
                .builder()
                .myApplicationComponent(MyApplication.getComponent())
                .build();
        component.injectBaseViewModel(this);
    }
}
