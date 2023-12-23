package com.ritanath.imagegallery;


import android.app.Application;

import com.ritanath.imagegallery.Retrofit.FlickrApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ViewModelComponent.class)
public class NetworkModule {

    @Provides
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public static FlickrApiService provideFlickrApiService(Retrofit retrofit) {
        return retrofit.create(FlickrApiService.class);
    }
}