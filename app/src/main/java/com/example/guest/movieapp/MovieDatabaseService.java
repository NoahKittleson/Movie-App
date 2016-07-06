package com.example.guest.movieapp;

/**
 * Created by Guest on 7/6/16.
 */



import android.provider.SyncStateContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

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

        //Log.d("something", APICall);
    }

    public static ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.d("MOVIE DATABASE SERVICE", jsonData);

            if (response.isSuccessful()) {
                JSONArray movieJSON = new JSONObject(jsonData).getJSONArray("list");

                for (int i = 0; i < movieJSON.length(); i++) {
                    String desc = movieJSON.getJSONObject(i).getJSONArray("movie").getJSONObject(0).getString("description");

                    Movie movie = new Movie(desc);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.d("MOVIE DATABASE SERVICE", jsonData);
        for (int i = 0; i < movies.size(); i++) {
            Log.d("MOVIE DATABASE SERVICE", movies.get(i).getTitle());
        }
        return movies;
    }
}