package diyarme.com.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by D.Kablaoui on 3/21/2017.
 */

public class MoviesList {

    @SerializedName("results")
    private ArrayList<MovieData> result;

    private String page;

    private String total_pages;

    private String total_results;

    public ArrayList<MovieData> getResult()
    {
        return result;
    }

    public void setResult(ArrayList<MovieData> result)
    {
        this.result = result;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+ result +", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }

}
