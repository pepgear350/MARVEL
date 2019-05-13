package com.pep.marvel.iu.fragment;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.pep.marvel.R;
import com.pep.marvel.ViewModel.ResultViewModel;
import com.pep.marvel.iu.Dialog;
import com.pep.marvel.util.DaggerApplication;
import com.pep.marvel.util.MarvelRepository;

import javax.inject.Inject;


public class ResultsFragment extends Fragment {



    private ResultViewModel viewModel;

    @Inject
    MarvelRepository repository;
    private View view;
    private ProgressBar progressBar;
    private InputMethodManager inputMethodManager;



    public ResultsFragment() {
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((DaggerApplication) getActivity().getApplication()).getMarvelComponent().inject(this);
        if (getActivity() != null) {
            inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_results_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ResultViewModel.class);

        Context context = view.getContext();
        RecyclerView recyclerView =  view.findViewById(R.id.list);
        progressBar = view.findViewById(R.id.progress_bar);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        TextInputEditText search = view.findViewById(R.id.edit_search);

        ImageView buttonSearch = view.findViewById(R.id.search_button);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.initRepository(repository);
        viewModel.initListDefault();
        viewModel.getMarvelEntity().observe(this, marvelEntities -> {

            if (marvelEntities.size() > 0) {
                adapter.setList(marvelEntities);
            }
        });


        viewModel.getListSearch().observe(this, marvelEntities -> {

            if (marvelEntities != null) {
                adapter.setList(marvelEntities);
            }


        });

        viewModel.getMessageError().observe(this, messageError -> {
            showSnackBar();
           showToast(messageError);
        });

        viewModel.getMessage().observe(this, this::showToast);

        viewModel.getTitleList().observe(this, titleList -> {
            search.setHint(titleList);
            showToast("UPDATED " + titleList);
        });

        viewModel.getIsProgress().observe(this, this::showProgress);

        buttonSearch.setOnClickListener(v -> {

            if (!search.getText().toString().isEmpty()) {
                inputMethodManager.hideSoftInputFromWindow(search.getWindowToken(),0);
                viewModel.searchDataBase(search.getText().toString(),false);
            }


        });



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    search.setCursorVisible(false);
                    viewModel.searchDataBase("", true);
                } else {
                    search.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        floatingActionButton.setOnClickListener(v -> {

            new Dialog().show(getFragmentManager(),"Dialog");
        });


    }



    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void showSnackBar() {
        Snackbar.make(view, getString(R.string.try_again), Snackbar.LENGTH_INDEFINITE)
                .show();
    }


    private void showToast(String text) {
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}
