package com.pep.marvel.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pep.marvel.db.entity.MarvelEntity;
import com.pep.marvel.model.response.DataWrapper;
import com.pep.marvel.model.response.Results;
import com.pep.marvel.util.MarvelRepository;

import java.util.List;

public class ResultViewModel extends ViewModel {


    private LiveData<List<MarvelEntity>> marvelEntity;
    private MutableLiveData<String> messageError;
    private MutableLiveData<String> message;
    private MutableLiveData<String> titleList;
    private MutableLiveData<List<MarvelEntity>> listSearch;
    private MutableLiveData<Boolean> isProgress;
    private MarvelRepository repository;

    public void initRepository(MarvelRepository repository) {
        this.repository = repository;
        repository.setViewModel(this);
    }


    public void initListDefault() {
        if (marvelEntity == null) {
            marvelEntity = new MutableLiveData<>();
        }
        marvelEntity = repository.getDefaultList();
    }



    public void searchDataBase(String name, Boolean isEmpty) {
        if (!isEmpty) {
            repository.getSearchList(name);
        } else {
            repository.loadAllEntitysSyn();
        }
    }

    public void initResult(int result) {
        repository.getResultList(result);
    }


    public LiveData<List<MarvelEntity>> getMarvelEntity() {
        return marvelEntity;
    }

    public MutableLiveData<String> getMessageError() {
        if (messageError == null) {
            messageError = new MutableLiveData<>();
        }
        return messageError;
    }


    public MutableLiveData<List<MarvelEntity>> getListSearch() {
        if (listSearch == null) {
            listSearch = new MutableLiveData<>();
        }
        return listSearch;
    }

    public MutableLiveData<String> getTitleList() {
        if (titleList == null) {
            titleList = new MutableLiveData<>();
        }
        return titleList;
    }

    public MutableLiveData<Boolean> getIsProgress() {
        if (isProgress == null) {
            isProgress = new MutableLiveData<>();
        }
        return isProgress;
    }

    public MutableLiveData<String> getMessage() {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        return message;
    }
}
