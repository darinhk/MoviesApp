package diyarme.com.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;



public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        controlSplashScreen();
    }

    private void controlSplashScreen() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startApp();
                finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,3*1000);
    }


    private void startApp() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
    }

}
