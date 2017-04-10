package diyarme.com.moviesapp;

import android.support.v4.app.Fragment;

/**
 * Created by D.Kablaoui on 3/16/2017.
 */

public interface FragmentCallBack {

    void passFragment( Fragment current);
    void passFragment( Fragment current,boolean addToBackStack);
}
