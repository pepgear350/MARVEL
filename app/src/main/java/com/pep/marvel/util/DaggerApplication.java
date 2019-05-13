package com.pep.marvel.util;

import android.app.Application;

import com.pep.marvel.di.DaggerMarvelComponent;
import com.pep.marvel.di.MarvelComponent;
import com.pep.marvel.di.RoomModule;

public class DaggerApplication extends Application {


    private MarvelComponent marvelComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        this.marvelComponent = DaggerMarvelComponent.builder()
                .roomModule(new RoomModule(this))
                .build();

    }


    public MarvelComponent getMarvelComponent() {
        return marvelComponent;
    }
}
