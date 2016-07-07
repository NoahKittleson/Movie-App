package com.example.guest.movieapp;

/**
 * Created by Guest on 7/7/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.PreviewViewHolder> {
    private ArrayList<Movie> mMovies;
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.PreviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_list_item, parent, false);
        PreviewViewHolder viewHolder = new PreviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.PreviewViewHolder holder, int position) {
        holder.bindPreview(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class PreviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.previewTitleTextView) TextView mTitleTextView;
        @Bind(R.id.previewDateTextView) TextView mDateTextView;
        @Bind(R.id.previewRatingTextView) TextView mRatingTextView;
        private Context mContext;

        public PreviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PreviewDetailActivity.class);
            intent.putExtra("movie", Parcels.wrap(mMovies.get(itemPosition)));
            Log.d("MovieListAdapy", mMovies.get(itemPosition).getTitle());
            mContext.startActivity(intent);
        }

        public void bindPreview(Movie movie) {
            mTitleTextView.setText(movie.getTitle());
            mDateTextView.setText(movie.getDate());
            mRatingTextView.setText(movie.getRating() + "/10");
        }
    }


}
