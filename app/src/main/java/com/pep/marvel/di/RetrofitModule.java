package com.pep.marvel.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pep.marvel.util.Constants.BASE_URL;

@Module
public class RetrofitModule {


    @Provides
    @Singleton
    public Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiMarvel provideMarvelApiService(Retrofit retrofit) {
        return retrofit.create(ApiMarvel.class);
    }





}
