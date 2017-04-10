package diyarme.com.moviesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import diyarme.com.moviesapp.models.MovieData;

/**
 * Created by D.Kablaoui on 3/15/2017.
 */

public class MovieDescriptionFragment extends Fragment {
    private ImageView iv_moviedescriptionimg;
    private TextView tv_descriptiontitle;
    private TextView tv_releasedate;
    private TextView tv_overview;
    private TextView tv_releasedatetext;
    private TextView tv_votecountext;
    private TextView tv_voteaveragetext;
    private TextView tv_adulttext;
    private TextView tv_languagetext;
    private TextView tv_id;
    private TextView tv_popularitytext;
    private TextView tv_videotext;
    private ImageView iv_sidemovieimg;
    private RatingBar mRatingBar;
    private SharedPreferences mSharedPref;
    private Bundle data;

    private MovieData mMovieData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.moviedescription_view, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_moviedescriptionimg = (ImageView) view.findViewById(R.id.iv_moviedescriptionimg);
        tv_descriptiontitle = (TextView) view.findViewById(R.id.tv_descriptiontitle);
        tv_releasedatetext = (TextView) view.findViewById(R.id.tv_releasedatetext);
        tv_languagetext = (TextView) view.findViewById(R.id.tv_languagetext);
        tv_adulttext = (TextView) view.findViewById(R.id.tv_adulttext);
        tv_videotext = (TextView) view.findViewById(R.id.tv_videotext);
        tv_popularitytext = (TextView) view.findViewById(R.id.tv_popularitytext);
        tv_voteaveragetext = (TextView) view.findViewById(R.id.tv_voteaveragetext);
        tv_votecountext = (TextView) view.findViewById(R.id.tv_votecounttext);
        tv_overview = (TextView) view.findViewById(R.id.tv_overview);
        iv_sidemovieimg = (ImageView) view.findViewById(R.id.iv_sidemovieimg);
        tv_id = (TextView) view.findViewById(R.id.tv_idtext);
        mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar2);
        mRatingBar.setEnabled(false);
        mSharedPref = getActivity().getSharedPreferences("favourites", Context.MODE_PRIVATE);
        setup();
    }

    public void setup() {
        data = getArguments();
        mMovieData = data.getParcelable("item");
        String posterpath = mMovieData.getPoster_path();
        getActivity().setTitle(mMovieData.getTitle().toUpperCase());

        Glide
                .with(getActivity())
                .load("https://image.tmdb.org/t/p/w500/" + posterpath).centerCrop()
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(iv_moviedescriptionimg);

        tv_popularitytext.setText(String.format("%s %.2f", "Popularity: ", mMovieData.getPopularity()));
        tv_votecountext.setText(String.format("%s %d", "Vote count: ", mMovieData.getVote_count()));
        tv_voteaveragetext.setText(String.format("%s %.2f", "Vote average: ", mMovieData.getVote_average()));
        tv_languagetext.setText(String.format("%s %s", "Language: ", mMovieData.getOriginal_language()));
        tv_releasedatetext.setText(String.format("%s %s", "Release date:", mMovieData.getRelease_date()));
        tv_videotext.setText(String.format("%s %s", "Video: ", mMovieData.isVideo() + ""));
        tv_adulttext.setText(String.format("%s %s", "Adult: ", mMovieData.getAdult() + ""));
        tv_id.setText(String.format("%s %d", "ID: ", mMovieData.getId()));
        tv_overview.setText(mMovieData.getOverview());
        tv_descriptiontitle.setText(mMovieData.getTitle().toUpperCase());
        Glide
                .with(getActivity())
                .load("https://image.tmdb.org/t/p/w500/" + posterpath)
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(iv_sidemovieimg);
        mRatingBar.setRating((float) (mMovieData.getVote_average() / 2));

        String value = mSharedPref.getString(data.get("position") + "", "null");
        if (!value.equals("null")) {
            Toast.makeText(getContext(), "This movie is one of your favourites!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tool_bar, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getActivity(), "Added to favourites!", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor edit = mSharedPref.edit();
        edit.putString(data.get("position") + "",mMovieData.getTitle().toString());
        edit.commit();
        return true;
    }


}
