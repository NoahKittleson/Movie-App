package com.example.guest.movieapp;

/**
 * Created by Guest on 7/6/16.
 */



import android.provider.SyncStateContract;
import android.util.Log;

import com.example.guest.movieapp.Constants;
import com.example.guest.movieapp.Preview;

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
        Log.d("MOVIEDBSERVIE", APICall);

        Request request = new Request.Builder()
                .url(APICall)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        //Log.d("something", APICall);
    }

    public static ArrayList<Preview> processResults(Response response) {
        ArrayList<Preview> previews = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject resultsJSON = new JSONObject(jsonData);
                JSONArray movieJSON = resultsJSON.getJSONArray("results");

                for (int i = 0; i < movieJSON.length(); i++) {
                    JSONObject filmJSON = movieJSON.getJSONObject(i);
                    String title = filmJSON.getString("title");
                    Log.d("MOVIEDBSERVIE", title + "");
                    String date = filmJSON.getString("release_date");
                    Log.d("MOVIEDBSERVIE", date + "");
                    int id = filmJSON.getInt("id");
                    Log.d("MOVIEDBSERVIE", id + "");
                    double rating = 0.0;//filmJSON.getDouble("rating");
                    Log.d("MOVIEDBSERVIE", rating + "");

                    Preview preview = new Preview(title, date, rating, id);
                    previews.add(preview);

                    Log.d("MOVIEDBSERVIE", previews.get(i).getTitle() + "");
                    Log.d("MOVIEDBSERVIE", previews.get(i).getDate() + "");
                    Log.d("MOVIEDBSERVIE", previews.get(i).getId() + "");
                    Log.d("MOVIEDBSERVIE", previews.get(i).getRating() + "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return previews;
    }
}