package com.pep.marvel.di;

import android.content.Context;

import androidx.room.Room;

import com.pep.marvel.db.MarvelDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private MarvelDataBase marvelDataBase;

    public RoomModule(Context context) {
        marvelDataBase = Room.databaseBuilder(context,
                MarvelDataBase.class, "marvel_database")
                .allowMainThreadQueries()
                .build();
    }


    @Provides
    @Singleton
    public MarvelDataBase provideMarverDataBase() {
        return marvelDataBase;
    }





}
