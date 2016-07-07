package com.example.guest.movieapp;

/**
 * Created by Guest on 7/6/16.
 */



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDatabaseService {

    public static void findMovie(String title, Callback callback) {
        String API_KEY = Constants.apiKey;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.themoviedb.org/3").newBuilder();
//        urlBuilder.addPathSegment("/search/movie?query=");
//        urlBuilder.addPathSegment(title);
//        urlBuilder.addPathSegment(Constants._API_THING);
//        urlBuilder.addPathSegment(Constants.apiKey);
//        String url = urlBuilder.build().toString();

        String APICall = "https://api.themoviedb.org/3/search/movie?query=" + title + "&api_key=" + Constants.apiKey;

        Request request = new Request.Builder()
                .url(APICall)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void ratedMovie(Callback callback) {
        String API_KEY = Constants.apiKey;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();


        String APICall = "https://api.themoviedb.org/3/discover/movie?primary_release_date.gte=2000-01-01&primary_release_date.lte=2020-01-01&sort_by=vote_average.desc&api_key=" + Constants.apiKey;

        Request request = new Request.Builder()
                .url(APICall)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void recentMovie(Callback callback) {
        String API_KEY = Constants.apiKey;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();


        String APICall = "https://api.themoviedb.org/3/discover/movie?primary_release_date.gte=2016-07-01&primary_release_date.lte=2016-08-01&api_key=" + Constants.apiKey;

        Request request = new Request.Builder()
                .url(APICall)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }


    public static ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject resultsJSON = new JSONObject(jsonData);
                JSONArray movieJSON = resultsJSON.getJSONArray("results");

                for (int i = 0; i < movieJSON.length(); i++) {
                    JSONObject filmJSON = movieJSON.getJSONObject(i);
                    String title = filmJSON.getString("title");
                    String date = filmJSON.getString("release_date");
                    String year = date.substring(0, 4);
                    int id = filmJSON.getInt("id");
                    double rating = filmJSON.getDouble("vote_average");
                    String synopsis = filmJSON.getString("overview");
                    String poster = filmJSON.getString("poster_path");

                    Movie movie = new Movie(title, year, rating, id, synopsis, poster);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}