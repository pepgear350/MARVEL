package com.pep.marvel.di;

import com.pep.marvel.iu.DetailsActivity;
import com.pep.marvel.iu.fragment.ResultsFragment;


import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {RetrofitModule.class, RoomModule.class, RepositoryModule.class})
public interface MarvelComponent {

    void inject (DetailsActivity detailsActivity);

    void inject(ResultsFragment resultsFragment);
}
