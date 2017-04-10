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

import butterknife.BindView;
import butterknife.ButterKnife;
import diyarme.com.moviesapp.models.MovieData;

/**
 * Created by D.Kablaoui on 3/15/2017.
 */

public class MovieDescriptionFragment extends Fragment {

    @BindView(R.id.iv_moviedescriptionimg)
    ImageView mImageMoviedescriptionimg;
    @BindView(R.id.tv_descriptiontitle)
    TextView mTextDescriptiontitle;
    @BindView(R.id.tv_overview)
    TextView mTextOverview;
    @BindView(R.id.tv_releasedatetext)
    TextView mTextReleasetext;
    @BindView(R.id.tv_votecounttext)
    TextView mTextVotecounttext;
    @BindView(R.id.tv_voteaveragetext)
    TextView mTextVoteaverage;
    @BindView(R.id.tv_adulttext)
    TextView mTextAdult;
    @BindView(R.id.tv_languagetext)
    TextView mTextLanguage;
    @BindView(R.id.tv_popularitytext)
    TextView mTextPopularity;
    @BindView(R.id.tv_videotext)
    TextView mTextVideo;
    @BindView(R.id.iv_sidemovieimg)
    ImageView mImageSidemovie;
    @BindView(R.id.ratingBar2)
    RatingBar mRatingBar;
    @BindView(R.id.tv_idtext)
    TextView mTextId;
    private SharedPreferences mSharedPref;
    private Bundle data;
    private MovieData mMovieData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.moviedescription_view, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                .into(mImageMoviedescriptionimg);

        mTextPopularity.setText(String.format("%s %.2f", "Popularity: ", mMovieData.getPopularity()));
        mTextVotecounttext.setText(String.format("%s %d", "Vote count: ", mMovieData.getVote_count()));
        mTextVoteaverage.setText(String.format("%s %.2f", "Vote average: ", mMovieData.getVote_average()));
        mTextLanguage.setText(String.format("%s %s", "Language: ", mMovieData.getOriginal_language()));
        mTextReleasetext.setText(String.format("%s %s", "Release date:", mMovieData.getRelease_date()));
        mTextVideo.setText(String.format("%s %s", "Video: ", mMovieData.isVideo() + ""));
        mTextAdult.setText(String.format("%s %s", "Adult: ", mMovieData.getAdult() + ""));
        mTextId.setText(String.format("%s %d", "ID: ", mMovieData.getId()));
        mTextOverview.setText(mMovieData.getOverview());
        mTextDescriptiontitle.setText(mMovieData.getTitle().toUpperCase());
        Glide
                .with(getActivity())
                .load("https://image.tmdb.org/t/p/w500/" + posterpath)
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(mImageSidemovie);
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
