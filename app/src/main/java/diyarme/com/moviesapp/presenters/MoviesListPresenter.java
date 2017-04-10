package diyarme.com.moviesapp.presenters;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import diyarme.com.moviesapp.models.MovieData;
import diyarme.com.moviesapp.models.MoviesList;
import diyarme.com.moviesapp.views.MoviesListView;




/**
 * Created by D.Kablaoui on 3/21/2017.
 */

public class MoviesListPresenter {

    private Context context;
    private ArrayList<MovieData> movielist;
    private MoviesListView view;


    public MoviesListPresenter(Context context, MoviesListView view) {
        this.context = context;
        this.view = view;
    }

    public void accessAPI() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://api.themoviedb.org/3/movie/popular?api_key=87a901020f496977f9d6d508c5d186ec";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

//                    movielist = getMoviesData(response);
                MoviesList moviesList = new MoviesList();
                Gson gson = new Gson();

                movielist = gson.fromJson(response, MoviesList.class).getResult();
                view.onComplete(movielist);
//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public ArrayList<MovieData> getMoviesData(String apidata) throws JSONException {
        ArrayList<MovieData> movieList = new ArrayList<MovieData>();
        JSONObject json = new JSONObject(apidata);
        JSONArray jArray = json.getJSONArray("results");

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jsonObject = jArray.getJSONObject(i);
            String overview = jsonObject.getString("overview");
            String release_date = jsonObject.getString("release_date");
            String title = jsonObject.getString("title");
            String poster_path = jsonObject.getString("poster_path");
            String original_language = jsonObject.getString("original_language");
            double popularity = jsonObject.getDouble("popularity");
            double vote_average = jsonObject.getDouble("vote_average");
            int id = jsonObject.getInt("id");
            int vote_count = jsonObject.getInt("vote_count");
            boolean adult = jsonObject.getBoolean("adult");
            boolean video = jsonObject.getBoolean("video");
            MovieData myObject = new MovieData(overview, release_date, title, poster_path, adult, id, original_language, popularity, vote_count, video, vote_average);
            movieList.add(myObject);


        }
        return movieList;
    }
}
