package com.pep.marvel.di;


import com.pep.marvel.db.MarvelDataBase;
import com.pep.marvel.util.MarvelRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public MarvelRepository provideMarvelRepository(ApiMarvel apiMarvel, MarvelDataBase dataBase) {
        return new MarvelRepository(apiMarvel, dataBase);
    }


}
