package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.PreviewListAdapter;
import com.example.guest.movieapp.Preview;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PreviewListActivity extends AppCompatActivity {
    //@Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private PreviewListAdapter mAdapter;

    public ArrayList<Preview> mPreviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        //getFullMovie(id);
    }
}
