package com.pep.marvel.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pep.marvel.model.response.DataWrapper;
import com.pep.marvel.util.MarvelRepository;

public class DetailsViewModel extends ViewModel {


    private MutableLiveData<String> messageError;
    private MutableLiveData<DataWrapper> resultDetails;
    private MutableLiveData<Boolean> isProgress;
    private MarvelRepository repository;

    public void initRepository(MarvelRepository repository) {
        this.repository = repository;
    }




    public void initDetails(String type , String id) {
        repository.getDetails(type , id , this);
    }



    public MutableLiveData<String> getMessageError() {
        if (messageError == null) {
            messageError = new MutableLiveData<>();
        }
        return messageError;
    }



    public MutableLiveData<Boolean> getIsProgress() {
        if (isProgress == null) {
            isProgress = new MutableLiveData<>();
        }
        return isProgress;
    }



    public MutableLiveData<DataWrapper> getResultDetails() {
        if (resultDetails == null) {
            resultDetails = new MutableLiveData<>();
        }
        return resultDetails;
    }


}
