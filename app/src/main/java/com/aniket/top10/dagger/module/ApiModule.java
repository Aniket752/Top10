package com.aniket.top10.dagger.module;

import com.aniket.top10.api.TopNewsApi;
import com.aniket.top10.api.apiImplementation.TopNewsApiImplementation;
import com.aniket.top10.news.repo.TopNewsRepo;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    TopNewsApi providesTopNewsApi(Retrofit retrofit){
        return retrofit.create(TopNewsApi.class);
    }

    @Provides
    TopNewsApiImplementation provideTopNewsApiImplementation(TopNewsApi topNewsApi){
        return new TopNewsApiImplementation(topNewsApi);
    }

    @Provides
    TopNewsRepo providesTopNewsRepo(){
        return new TopNewsRepo();
    }
}
