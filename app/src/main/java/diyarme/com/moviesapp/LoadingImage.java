package diyarme.com.moviesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by D.Kablaoui on 3/15/2017.
 */

public class LoadingImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public LoadingImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bmImage.setImageResource(R.drawable.placeholder);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urldisplay = params[0];
        Bitmap image = null;

        InputStream in = null;
        try {
            in = new java.net.URL(urldisplay).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
