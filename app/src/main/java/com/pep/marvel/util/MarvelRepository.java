package com.pep.marvel.util;


import androidx.lifecycle.LiveData;

import com.pep.marvel.ViewModel.DetailsViewModel;
import com.pep.marvel.ViewModel.ResultViewModel;
import com.pep.marvel.db.MarvelDataBase;
import com.pep.marvel.db.entity.MarvelEntity;
import com.pep.marvel.di.ApiMarvel;
import com.pep.marvel.util.update.UpdateDataBase;
import com.pep.marvel.util.update.UpdateDetailsItem;

import java.util.List;

public class MarvelRepository {

    private ApiMarvel apiMarvel;
    private MarvelDataBase dataBase;
    private ResultViewModel viewModel;
    private UpdateDataBase updateDataBase;


    public MarvelRepository(ApiMarvel apiMarvel, MarvelDataBase dataBase) {
        this.apiMarvel = apiMarvel;
        this.dataBase = dataBase;
    }



    public LiveData<List<MarvelEntity>> getDefaultList() {
        updateDataBase(Constants.DEFAULT);
        return dataBase.marvelDAO().loadAllEntitys();
    }


    public void getResultList(int result) {
        updateDataBase(result);
    }

    private void updateDataBase(int result) {
        updateDataBase.doUpdate(result);
    }

    public void setViewModel(ResultViewModel viewModel) {
        this.viewModel = viewModel;
        this.updateDataBase =  new UpdateDataBase(dataBase, apiMarvel, viewModel);
    }


    public void getSearchList(String name) {
        List<MarvelEntity> list = dataBase.marvelDAO().loadLikeEntitys(name);
        viewModel.getListSearch().setValue(list);
    }


    public void loadAllEntitysSyn () {
        List<MarvelEntity> list = dataBase.marvelDAO().loadAllEntitysSyn();
        viewModel.getListSearch().setValue(list);
    }


    public void getDetails(String type, String id , DetailsViewModel viewModel) {
        try {
            new UpdateDetailsItem(apiMarvel, viewModel).doUpdate(type, id);
        } catch (Exception ignored) {
        }
    }
}
