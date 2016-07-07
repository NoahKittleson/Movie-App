package com.example.guest.movieapp;

import org.parceler.Parcel;

/**
 * Created by Guest on 7/7/16.
 */
@Parcel
public class Movie {
    private String mTitle;
    private String mDate;
    private double mRating;
    private int mId;
    private String mSynopsis;
    private String mPoster;
    private String[] mActors;

    public Movie() {}

    public Movie(String title, String date, double rating, int id, String synopsis, String poster) {
        this.mTitle = title;
        this.mDate = date;
        this.mRating = rating;
        this.mId = id;
        this.mSynopsis = synopsis;
        this.mPoster = poster;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public int getId() {
        return mId;
    }

    public double getRating() {
        return mRating;
    }

    public String getSynopsis() { return mSynopsis; }

    public String getPoster() { return mPoster; }

}

