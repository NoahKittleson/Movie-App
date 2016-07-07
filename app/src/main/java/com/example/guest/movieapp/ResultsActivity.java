package com.example.guest.movieapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ResultsActivity extends AppCompatActivity {
    public ArrayList<Movie> mMovies = new ArrayList<>();
    private MovieListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        if (title.equals("recent")) {
            getRecentMovie();
        } else if (title.equals("rating")) {
            getRatedMovie();
        } else {
            getMovie(title);
        }
    }

    private void getMovie(String title) {
        final MovieDatabaseService movieDatabaseService = new MovieDatabaseService();

        movieDatabaseService.findMovie(title, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = MovieDatabaseService.processResults(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Log.d("Results Activity: ", mMovies.size() + "");
                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    private void getRatedMovie() {
        final MovieDatabaseService movieDatabaseService = new MovieDatabaseService();

        movieDatabaseService.ratedMovie(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = MovieDatabaseService.processResults(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Log.d("Results Activity: ", mMovies.size() + "");
                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    private void getRecentMovie() {
        final MovieDatabaseService movieDatabaseService = new MovieDatabaseService();

        movieDatabaseService.recentMovie(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = MovieDatabaseService.processResults(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Log.d("Results Activity: ", mMovies.size() + "");
                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
