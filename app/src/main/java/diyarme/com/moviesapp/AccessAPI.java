package diyarme.com.moviesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by D.Kablaoui on 3/15/2017.
 */
public class AccessAPI extends AsyncTask<Void, Void, String> {
    ProgressDialog progressdialog;
    private APICallBack callBack;



    public AccessAPI(Context context) {
        progressdialog = new ProgressDialog(context);
    }
    @Override
    protected String doInBackground(Void... params) {
        URL url = null;
        try {
            url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=87a901020f496977f9d6d508c5d186ec");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        HttpURLConnection urlconnection = null;
        InputStream input = null;
        try {
            urlconnection = (HttpURLConnection)url.openConnection();
            input = new BufferedInputStream(urlconnection.getInputStream());
            String value = readStream(input, 1024*90);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlconnection.disconnect();
        }
        return null;
    }

    public void setCallBack(APICallBack callBack) {
        this.callBack = callBack;
    }

    protected void onPostExecute(String data) {
        super.onPostExecute(data);
        if (callBack != null)
            callBack.result(data);
        progressdialog.dismiss();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressdialog.setMessage("loading");
        progressdialog.show();
    }

    public String readStream(InputStream input, int max) throws IOException {
        String result = null;

        InputStreamReader reader = new InputStreamReader(input, "UTF-8");

        char[] buffer = new char[max];
        int chars = 0;
        int readsize = 0;
        while (chars < max && readsize != -1) {
            chars += readsize;
            readsize = reader.read(buffer, chars, buffer.length - chars);
        }
        if (chars != -1) {
            chars = Math.min(chars, max);
            result = new String(buffer, 0, chars);
        }

        return result;

    }

}
