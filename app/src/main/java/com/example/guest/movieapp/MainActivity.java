package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.movieapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.titleButton) Button mTitleButton;
    @Bind(R.id.recentButton) Button mRecentButton;
    @Bind(R.id.ratingButton) Button mRatingButton;
    @Bind(R.id.titleEditText) EditText mTitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTitleButton.setOnClickListener(this);
        mRatingButton.setOnClickListener(this);
        mRecentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mTitleButton) {
            String title = mTitleEditText.getText().toString();
            Intent titleIntent = new Intent(MainActivity.this, ResultsActivity.class);
            titleIntent.putExtra("title", title);
            startActivity(titleIntent);
        }
        if(view == mRecentButton) {
            Intent recentIntent = new Intent(MainActivity.this, ResultsActivity.class);
            recentIntent.putExtra("title", "recent");
            startActivity(recentIntent);
        }
        if(view == mRatingButton) {
            Intent ratingIntent = new Intent(MainActivity.this, ResultsActivity.class);
            ratingIntent.putExtra("title", "rating");
            startActivity(ratingIntent);
        }
    }
}
