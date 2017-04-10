package diyarme.com.moviesapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import diyarme.com.moviesapp.FragmentCallBack;
import diyarme.com.moviesapp.R;


public class MainActivity extends AppCompatActivity implements FragmentCallBack {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment moviesList = new MoviesListFragment();
        passFragment(moviesList);
    }

    @Override
    public void passFragment(Fragment current) {
        passFragment(current,false);
    }

    @Override
    public void passFragment(Fragment current, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, current).commit();
    }
}