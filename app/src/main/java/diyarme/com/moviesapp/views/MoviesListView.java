package diyarme.com.moviesapp.views;

import java.util.ArrayList;

import diyarme.com.moviesapp.models.MovieData;

/**
 * Created by D.Kablaoui on 3/21/2017.
 */

public interface MoviesListView {
    void onComplete(ArrayList<MovieData> movielist);
}
