package com.pep.marvel.iu;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.pep.marvel.R;
import com.pep.marvel.ViewModel.DetailsViewModel;
import com.pep.marvel.di.MarvelComponent;
import com.pep.marvel.model.Image;
import com.pep.marvel.model.Items;
import com.pep.marvel.model.ResourceList;
import com.pep.marvel.model.response.DataWrapper;
import com.pep.marvel.model.response.Results;
import com.pep.marvel.util.Constants;
import com.pep.marvel.util.MarvelRepository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity {


    @Inject
    MarvelRepository repository;
    private DetailsViewModel viewModel;
    private ProgressBar progressBar;
    private View view;
    private ImageView mImage;
    private TextView mTitle;
    private LinearLayout layout_character;
    private LinearLayout layout_comics;
    private LinearLayout layout_creators;
    private LinearLayout layout_events;
    private LinearLayout layout_series;
    private LinearLayout layout_stories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        viewModel.initRepository(repository);
        view = findViewById(R.id.view_container);
        mImage = findViewById(R.id.details_image);
        mTitle = findViewById(R.id.details_title);
        progressBar = findViewById(R.id.details_progress_bar);
        layout_character = findViewById(R.id.linearLayout_character);
        layout_comics = findViewById(R.id.linearLayout_comics);
        layout_creators = findViewById(R.id.linearLayout_creators);
        layout_events = findViewById(R.id.linearLayout_events);
        layout_series = findViewById(R.id.linearLayout_series);
        layout_stories = findViewById(R.id.linearLayout_stories);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String id = bundle.getString(Constants.BUNDLE_ID);
            String type = bundle.getString(Constants.BUNDLE_TYPE);
            viewModel.initDetails(type,id);
        }


        viewModel.getResultDetails().observe(this, dataWrapper -> {
            handler.post(new DetailsRunnable(dataWrapper));
        });


        viewModel.getIsProgress().observe(this, this::showProgress);

        viewModel.getMessageError().observe(this, this::showSnackBar);


    }


    Handler handler = new Handler();

    private class DetailsRunnable implements Runnable {


        private DataWrapper dataWrapper;

        private DetailsRunnable(DataWrapper dataWrapper) {
            this.dataWrapper = dataWrapper;
        }


        @Override
        public void run() {


            Results[] results = dataWrapper.getData().getResults();

            ResourceList character = results[0].getCharacters();
            ResourceList comics = results[0].getComics();
            ResourceList creators = results[0].getCreators();
            ResourceList events = results[0].getEvents();
            ResourceList series = results[0].getSeries();
            ResourceList stories = results[0].getStories();


            Image image = results[0].getThumbnail();
            String url = null;
            if (image != null) {
                String size = "/standard_large.";
                url = image.getPath() + size + image.getExtesion();
            }

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(new ColorDrawable(ContextCompat.getColor(DetailsActivity.this, R.color.colorAccent)));
            requestOptions.error(R.drawable.ic_photo_black_24dp);
            requestOptions.circleCrop();


            Glide.with(DetailsActivity.this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(mImage);

            String name = "";

            if (results[0].getName() != null) {
                name = results[0].getName();
            }

            if (results[0].getTitle() != null) {
                name = results[0].getTitle();
            }

            if (results[0].getFullName() != null) {
                name = results[0].getFullName();
            }

            mTitle.setText(name);

            if (character != null) {
                Items[] items = character.getItems();

                if (items != null) {

                    boolean isGone = true;
                    for (Items itemsCh : items) {

                        if (isGone) {
                            layout_character.setVisibility(View.VISIBLE);
                            isGone = false;
                        }

                        View viewCh;
                        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewCh = inflater.inflate(R.layout.details_item, null);

                        TextView txtCh =  viewCh.findViewById(R.id.text_item);
                        String titleCh = itemsCh.getName();
                        if (titleCh != null) {
                            txtCh.setText(titleCh);
                        }
                        layout_character.addView(viewCh);
                    }
                }

            }

            if (comics != null) {
                Items[] items = comics.getItems();

                if (items != null) {

                    boolean isGone = true;
                    for (Items itemsC : items) {


                        if (isGone) {
                            layout_comics.setVisibility(View.VISIBLE);
                            isGone = false;
                        }
                        View viewC;
                        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewC = inflater.inflate(R.layout.details_item, null);

                        TextView txtC =  viewC.findViewById(R.id.text_item);
                        String titleC = itemsC.getName();
                        if (titleC != null) {
                            txtC.setText(titleC);
                        }
                        layout_comics.addView(viewC);
                    }

                }

            }


            if (creators != null) {
                Items[] items = creators.getItems();

                if (items != null) {

                    boolean isGone = true;
                    for (Items itemsCr : items) {


                        if (isGone) {
                            layout_creators.setVisibility(View.VISIBLE);
                            isGone = false;
                        }
                        View viewCr;
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewCr = inflater.inflate(R.layout.details_item, null);

                        TextView txtCr = viewCr.findViewById(R.id.text_item);
                        String titleCr = itemsCr.getName();
                        if (titleCr != null) {
                            txtCr.setText(titleCr);
                        }
                        layout_creators.addView(viewCr);
                    }
                }
            }



            if (events != null) {
                Items[] items = events.getItems();

                if (items != null) {

                    boolean isGone = true;

                    for (Items itemsE : items) {

                        if (isGone) {
                            layout_events.setVisibility(View.VISIBLE);
                            isGone = false;
                        }
                        View viewE;
                        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewE = inflater.inflate(R.layout.details_item, null);

                        TextView txtE =  viewE.findViewById(R.id.text_item);
                        String titleE = itemsE.getName();
                        if (titleE != null) {
                            txtE.setText(titleE);
                        }
                        layout_events.addView(viewE);
                    }
                }

            }



            if (series != null) {
                Items[] items = series.getItems();

                if (items != null) {
                    boolean isGone = true;

                    for (Items itemsSe : items) {

                        if (isGone) {
                            layout_series.setVisibility(View.VISIBLE);
                            isGone = false;
                        }

                        View viewSe;
                        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewSe = inflater.inflate(R.layout.details_item, null);

                        TextView txtSe =  viewSe.findViewById(R.id.text_item);
                        String titleSe = itemsSe.getName();
                        if (titleSe != null) {
                            txtSe.setText(titleSe);
                        }
                        layout_series.addView(viewSe);
                    }
                }

            }


            if (stories != null) {
                Items[] items = stories.getItems();

                if (items != null) {
                    boolean isGone = true;
                    for (Items itemsSt : items) {

                        if (isGone) {
                            layout_stories.setVisibility(View.VISIBLE);
                            isGone = false;
                        }

                        View viewSt;
                        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewSt = inflater.inflate(R.layout.details_item, null);

                        TextView txtSt =  viewSt.findViewById(R.id.text_item);
                        String titleSt = itemsSt.getName();
                        if (titleSt != null) {
                            txtSt.setText(titleSt);
                        }
                        layout_stories.addView(viewSt);
                    }
                }

            }

            handler.removeCallbacks(this);

        }
    }



    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void showSnackBar(String s) {

        Snackbar.make(view, s, Snackbar.LENGTH_INDEFINITE)
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void injectDependencies(MarvelComponent marvelComponent) {
        marvelComponent.inject(this);
    }
}
