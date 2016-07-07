package com.example.guest.movieapp;

/**
 * Created by Guest on 7/6/16.
 */
public class Movie {
    private String mTitle;
    private String mDirector;
    private String mSynopsis;
    private String mPoster;
    private String[] mActors;
    private double mRating;

    public Movie(String title, String director, String synopsis, String poster, double rating) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}
