package com.pep.marvel.util.update;

import com.pep.marvel.ViewModel.DetailsViewModel;
import com.pep.marvel.ViewModel.ResultViewModel;
import com.pep.marvel.di.ApiMarvel;
import com.pep.marvel.model.ApiError;
import com.pep.marvel.model.response.DataWrapper;
import com.pep.marvel.util.Constants;
import com.pep.marvel.util.Hash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsItem {

    private ApiMarvel apiMarvel;
    private DetailsViewModel viewModel;

    public UpdateDetailsItem(ApiMarvel apiMarvel, DetailsViewModel viewModel) {
        this.apiMarvel = apiMarvel;
        this.viewModel = viewModel;
    }


    public void doUpdate(String type, String id) {
        viewModel.getIsProgress().setValue(true);
        long ts = 1;
        String hash = Hash.generate(ts, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);
        Call<DataWrapper> call;

        switch (type) {
            case Constants.UPDATED_CHARACTERS:
                call = apiMarvel.getCharacterID(id, Constants.PUBLIC_KEY, hash, ts);

                break;
            case Constants.UPDATED_COMICS:
                call = apiMarvel.getComicsID(id,Constants.PUBLIC_KEY, hash, ts);

                break;

            case Constants.UPDATED_CREATORS:
                call = apiMarvel.getCreatorsID(id,Constants.PUBLIC_KEY, hash, ts);

                break;
            case Constants.UPDATED_EVENTS:
                call = apiMarvel.getEventsID(id,Constants.PUBLIC_KEY, hash, ts);

                break;
            case Constants.UPDATED_SERIES:
                call = apiMarvel.getSeriesID(id,Constants.PUBLIC_KEY, hash, ts);

                break;

            case Constants.UPDATED_STORIES:
                call = apiMarvel.getStoriesID(id,Constants.PUBLIC_KEY, hash, ts);

                break;

            default:
                call = apiMarvel.getStoriesID(id,Constants.PUBLIC_KEY, hash, ts);
                break;
        }


        call.enqueue(new Callback<DataWrapper>() {
            @Override
            public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {

                int code = response.code();


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
                viewModel.getIsProgress().setValue(false);
                viewModel.getResultDetails().setValue(dataWrapper);

            }

            @Override
            public void onFailure(Call<DataWrapper> call, Throwable t) {
                viewModel.getIsProgress().setValue(false);
                viewModel.getMessageError().setValue(t.getMessage());
            }
        });
    }



}
