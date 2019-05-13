package com.pep.marvel.iu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pep.marvel.di.MarvelComponent;
import com.pep.marvel.util.DaggerApplication;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(getMarvelComponent());
    }

    private MarvelComponent getMarvelComponent() {
       return  ((DaggerApplication) getApplication()).getMarvelComponent();
    }

    protected abstract void injectDependencies(MarvelComponent marvelComponent);
}
