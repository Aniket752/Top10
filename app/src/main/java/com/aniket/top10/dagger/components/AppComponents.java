package com.aniket.top10.dagger.components;

import com.aniket.top10.api.apiImplementation.TopNewsApiImplementation;
import com.aniket.top10.dagger.module.ApiModule;
import com.aniket.top10.dagger.module.MyApplicationModule;
import com.aniket.top10.dagger.module.NetworkModule;
import com.aniket.top10.dagger.scopes.ApplicationScope;
import com.aniket.top10.database.AppDatabase;

import dagger.Component;

@Component(modules = {ApiModule.class, NetworkModule.class, MyApplicationModule.class})
public interface AppComponents {

    TopNewsApiImplementation getTopNewsApiImplementation();

    AppDatabase getAppDatabase();
}
