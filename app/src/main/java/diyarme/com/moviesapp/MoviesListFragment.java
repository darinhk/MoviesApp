package diyarme.com.moviesapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import diyarme.com.moviesapp.models.MovieData;
import diyarme.com.moviesapp.presenters.MoviesListPresenter;
import diyarme.com.moviesapp.views.MoviesListView;


/**
 * Created by D.Kablaoui on 3/15/2017.
 */

//public class MoviesListFragment extends Fragment implements APICallBack, PositionClickListener {
public class MoviesListFragment extends Fragment implements PositionClickListener, MoviesListView {
    @BindView(R.id.rv_movieslist)
    RecyclerView rv_moviesdata;
    private FragmentCallBack callBack;
    private MoviesListPresenter mPresenter;
    private MyAdapter myadapter;

    public static MoviesListFragment newInstance() {
        MoviesListFragment moviesListFragment = new MoviesListFragment();
        Bundle bundle = new Bundle();
        moviesListFragment.setArguments(bundle);
        return moviesListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBack = (FragmentCallBack) getActivity();
        } catch (Exception e) {
            Log.d("fragment", "Class cast exception");
        }
    }

    public MoviesListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movieslist_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_moviesdata.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter = new MoviesListPresenter(getContext(), this);
        mPresenter.accessAPI();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
    }
/*
   @Override
    public void result(String apidata) {
        this.apidata = apidata;
        if (!TextUtils.isEmpty(apidata)) {
        Log.d("apidata", apidata);
            try {
                movielist = getMoviesData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MyAdapter myadapter = new MyAdapter(getContext(), movielist);
            myadapter.setCallBack(this);
            rv_moviesdata.setAdapter(myadapter);
        }
    }
*/
  /* public void accessAPI() {
        AccessAPI access = new AccessAPI(getActivity());
        access.setCallBack(this);
        access.execute();
    }
*/


    @Override
    public void itemClicked(int position) {
        Bundle data = new Bundle();
        data.putParcelable("item", myadapter.getItem(position));
        data.putInt("position", position);
        MovieDescriptionFragment movieDescriptionFragment = new MovieDescriptionFragment();
        movieDescriptionFragment.setArguments(data);
        callBack.passFragment(movieDescriptionFragment, true);
    }


    @Override
    public void onComplete(ArrayList<MovieData> movielist) {
         myadapter = new MyAdapter(getContext(), movielist);
        myadapter.setCallBack(MoviesListFragment.this);
        rv_moviesdata.setAdapter(myadapter);
    }
}
