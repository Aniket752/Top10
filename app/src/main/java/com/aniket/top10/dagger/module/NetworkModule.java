package com.aniket.top10.dagger.module;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;

import com.aniket.top10.dagger.qualifier.ApplicationContext;
import com.aniket.top10.database.AppDatabase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final int READ_TIMEOUT = 60 ;
    private static final int WRITE_TIMEOUT = 60 ;

    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("appSource","Top10").build());
            }
        })
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
                .build();
    }

    @Provides
    CallAdapter.Factory providesRetrofitCallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    Converter.Factory providesRetrofitConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient, CallAdapter.Factory callAdapterFactory, Converter.Factory converterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();

    }

    @Provides
    AppDatabase providesRoomDataBase(@ApplicationContext Context context){
        return AppDatabase.getInstance(context);
    }


}
