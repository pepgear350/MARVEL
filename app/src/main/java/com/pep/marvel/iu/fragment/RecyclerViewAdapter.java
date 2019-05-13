package com.pep.marvel.iu.fragment;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pep.marvel.R;
import com.pep.marvel.ViewModel.ResultViewModel;
import com.pep.marvel.db.entity.MarvelEntity;
import com.pep.marvel.iu.DetailsActivity;
import com.pep.marvel.util.Constants;


import java.util.Collections;
import java.util.List;




public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {



    private List<MarvelEntity> list = Collections.emptyList();
    private Context context;
    private ResultViewModel viewModel;

    public RecyclerViewAdapter(Context context, ResultViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_results_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mTittle.setText(list.get(position).getName());
        holder.mAttribution.setText(list.get(position).getAttributionText());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)));
        requestOptions.error(R.drawable.ic_photo_black_24dp);
        requestOptions.centerCrop();

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(list.get(position).getThumbnail())
                .into(holder.imageView);

        holder.mView.setOnClickListener(v -> {

            String id = String.valueOf(list.get(position).getId());
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(Constants.BUNDLE_ID, id);
            intent.putExtra(Constants.BUNDLE_TYPE, viewModel.getTitleList().getValue());
            context.startActivity(intent);

        });

    }

    void setList(List<MarvelEntity> marvelEntities) {
        list = marvelEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTittle;
        public final TextView mAttribution;
        public final ImageView imageView;


        public ViewHolder(View view) {
            super(view);

            mView = view;
            mTittle =  view.findViewById(R.id.item_name);
            imageView = view.findViewById(R.id.item_image);
            mAttribution = view.findViewById(R.id.item_attribution);
        }


    }
}
