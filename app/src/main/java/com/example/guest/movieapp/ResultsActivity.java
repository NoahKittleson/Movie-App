package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ResultsActivity extends AppCompatActivity {
    @Bind(R.id.textView2) TextView mTextView2;
    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent titleIntent = getIntent();
        String title = titleIntent.getStringExtra("title");
        mTextView2.setText(title);
        getMovie(title);
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
//                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixed(true);
                    }
                });
            }
        });
    }
}
