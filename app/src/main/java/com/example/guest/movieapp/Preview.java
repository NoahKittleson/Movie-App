package com.example.guest.movieapp;

/**
 * Created by Guest on 7/7/16.
 */
public class Preview {
    private String mTitle;
    private String mDate;
    private double mRating;
    private int mId;

    public Preview(String title, String date, double rating, int id) {
        this.mTitle = title;
        this.mDate = date;
        this.mRating = rating;
        this.mId = id;
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
}

