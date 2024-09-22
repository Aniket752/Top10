package com.aniket.top10.dagger.components;


import com.aniket.top10.MyApplication;
import com.aniket.top10.api.apiImplementation.TopNewsApiImplementation;
import com.aniket.top10.dagger.module.ApiModule;
import com.aniket.top10.dagger.module.MyApplicationModule;
import com.aniket.top10.dagger.module.NetworkModule;
import com.aniket.top10.database.AppDatabase;

import dagger.Component;

@Component(modules = {MyApplicationModule.class, NetworkModule.class, ApiModule.class})
public interface MyApplicationComponent {
    void injectMyApplication(MyApplication myApplication);

    MyApplication getMyApplication();
    TopNewsApiImplementation getTopNewsApiImplementation();

    AppDatabase getAppDatabase();
}
