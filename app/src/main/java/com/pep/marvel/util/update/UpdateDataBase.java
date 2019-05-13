package com.pep.marvel.util.update;

import android.os.AsyncTask;

import com.pep.marvel.ViewModel.ResultViewModel;
import com.pep.marvel.db.MarvelDataBase;
import com.pep.marvel.db.entity.MarvelEntity;
import com.pep.marvel.di.ApiMarvel;
import com.pep.marvel.model.ApiError;
import com.pep.marvel.model.Image;
import com.pep.marvel.model.response.Results;
import com.pep.marvel.model.response.DataWrapper;
import com.pep.marvel.util.Constants;
import com.pep.marvel.util.Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateDataBase {

    private MarvelDataBase dataBase;
    private ApiMarvel apiMarvel;
    private ResultViewModel viewModel;

    public UpdateDataBase(MarvelDataBase dataBase, ApiMarvel apiMarvel, ResultViewModel viewModel) {
        this.dataBase = dataBase;
        this.apiMarvel = apiMarvel;
        this.viewModel = viewModel;
    }


    public void doUpdate(int result) {
        viewModel.getIsProgress().setValue(true);
        long ts = 1;
        String hash = Hash.generate(ts, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);
        String titleList;
        Call<DataWrapper> call;

        switch (result) {
            case 0:
                call = apiMarvel.getCharacter(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_CHARACTERS;
                break;
            case 3:
                call = apiMarvel.getComics(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_COMICS;
                break;
            case 5:
                call = apiMarvel.getComics(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_COMICS;
                break;
            case 7:
                call = apiMarvel.getCreators(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_CREATORS;
                break;
            case 11:
                call = apiMarvel.getEvents(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_EVENTS;
                break;
            case 13:
                call = apiMarvel.getSeries(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_SERIES;
                break;

            case Constants.DEFAULT:
                call = apiMarvel.getStories(Constants.PUBLIC_KEY, hash, ts);
                titleList = Constants.UPDATED_STORIES;
                break;

                default:
                    call = apiMarvel.getStories(Constants.PUBLIC_KEY, hash, ts);
                    titleList = Constants.UPDATED_STORIES;
                    break;
        }


        call.enqueue(new Callback<DataWrapper>() {
            @Override
            public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {

                int code = response.code();

                if (code == 409) {
                    if (response.body() != null) {
                        viewModel.getIsProgress().setValue(false);
                        viewModel.getMessageError().setValue(response.body().getStatus());
                    }
                    return;
                }

                if (code == 404) {
                    if (response.body() != null) {
                        viewModel.getIsProgress().setValue(false);
                        viewModel.getMessageError().setValue(response.body().getStatus());
                    }
                    return;
                }

                if (!response.isSuccessful()) {

                    if (response.errorBody() != null) {
                        if (response.errorBody()
                                .contentType()
                                .subtype()
                                .equals("json")) {
                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                            if (apiError != null) {
                                viewModel.getIsProgress().setValue(false);
                                viewModel.getMessageError().setValue(apiError.getMessage());
                            }

                        } else {
                            viewModel.getIsProgress().setValue(false);
                            viewModel.getMessageError().setValue(response.errorBody().toString());
                        }
                    }
                    return;
                }

                DataWrapper dataWrapper = response.body();
                new InsertAsyncTask(dataBase, dataWrapper).execute();
                viewModel.getIsProgress().setValue(false);
                viewModel.getTitleList().setValue(titleList);

            }

            @Override
            public void onFailure(Call<DataWrapper> call, Throwable t) {
                viewModel.getIsProgress().setValue(false);
                viewModel.getMessageError().setValue(t.getMessage());
            }
        });
    }


    private static class InsertAsyncTask extends AsyncTask<Void, Void, Void> {

        private MarvelDataBase dataBase;
        private DataWrapper dataWrapper;


        InsertAsyncTask(MarvelDataBase dataBase, DataWrapper dataWrapper) {
            this.dataBase = dataBase;
            this.dataWrapper = dataWrapper;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (dataWrapper != null) {
                Results[] results = dataWrapper.getData().getResults();
                List<MarvelEntity> entityList = getEntity(Arrays.asList(results),dataWrapper.getAttributionText());
                dataBase.marvelDAO().deleteAll();
                dataBase.marvelDAO().saveEntitys(entityList);
            }
            return null;
        }


        private List<MarvelEntity> getEntity(List<Results> resultsList , String attributionText) {

            List<MarvelEntity> list = new ArrayList<>();

            for (Results results : resultsList) {

                Image image = results.getThumbnail();
                String url = null;
                if (image != null) {
                    String size = "/landscape_medium.";
                    url = image.getPath() + size + image.getExtesion();
                }

                String name = "";

                if (results.getName() != null) {
                    name = results.getName();
                }

                if (results.getTitle() != null) {
                    name = results.getTitle();
                }

                if (results.getFullName() != null) {
                    name = results.getFullName();
                }

                list.add(new MarvelEntity(results.getId(), name , url, attributionText));

            }

            return list;
        }


    }
}
