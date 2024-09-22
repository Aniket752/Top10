package com.aniket.top10;

import android.app.Application;

import com.aniket.top10.dagger.components.AppComponents;
import com.aniket.top10.dagger.components.DaggerMyApplicationComponent;
import com.aniket.top10.dagger.components.MyApplicationComponent;
import com.aniket.top10.dagger.module.MyApplicationModule;

public class MyApplication extends Application {

    static MyApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMyApplicationComponent
                .builder()
                .myApplicationModule(new MyApplicationModule(this))
                .build();
        component.injectMyApplication(this);
    }

    public static MyApplicationComponent getComponent(){
        return component;
    }
}
