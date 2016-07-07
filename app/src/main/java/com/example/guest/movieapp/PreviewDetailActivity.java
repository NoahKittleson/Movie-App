package com.example.guest.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.movieapp.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PreviewDetailActivity extends AppCompatActivity {
    Movie mMovie;
    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.synopsisView) TextView mSynopsisView;
    @Bind(R.id.ratingView) TextView mRatingView;
    @Bind(R.id.posterView) ImageView mPosterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_detail);
        ButterKnife.bind(this);

        mMovie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        mTitleView.setText(mMovie.getTitle());
        mSynopsisView.setText(mMovie.getSynopsis());
        mRatingView.setText(mMovie.getRating() + "/10");
        String url = "http://image.tmdb.org/t/p/w500" + mMovie.getPoster();
        Picasso.with(this).load(url).into(mPosterView);
        //mPosterView.url("www.baseurl/2" + mMovie.getPoster());

        //int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

    }
}
