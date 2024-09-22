package com.aniket.top10.dagger.module;

import android.content.Context;

import com.aniket.top10.MyApplication;
import com.aniket.top10.dagger.components.MyApplicationComponent;
import com.aniket.top10.dagger.qualifier.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class MyApplicationModule {
    private MyApplication myApplication;

    public MyApplicationModule(MyApplication myApplication){
        this.myApplication = myApplication;
    }

    @Provides
    MyApplication providesMyApplication(){
        return myApplication;
    }

    @Provides
    @ApplicationContext
    Context getApplicationContext(){
        return myApplication.getApplicationContext();
    }

}
