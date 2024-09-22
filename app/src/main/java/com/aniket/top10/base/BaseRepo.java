package com.aniket.top10.base;

import com.aniket.top10.MyApplication;
import com.aniket.top10.dagger.components.DaggerRepoComponent;
import com.aniket.top10.dagger.components.RepoComponent;

public class BaseRepo {
    protected RepoComponent component;

    public BaseRepo(){
        component = DaggerRepoComponent.builder()
                .myApplicationComponent(MyApplication.getComponent())
                .build();
        component.injectBaseRepo(this);
    }
}
