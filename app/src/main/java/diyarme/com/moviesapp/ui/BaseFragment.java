package diyarme.com.moviesapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import diyarme.com.moviesapp.R;

/**
 * Created by alahammad on 4/10/17.
 */

public abstract class BaseFragment extends Fragment {

    abstract int setViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setViewId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
