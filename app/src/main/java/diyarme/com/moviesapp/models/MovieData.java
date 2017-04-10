package diyarme.com.moviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by D.Kablaoui on 3/15/2017.
 */

public class MovieData implements Parcelable{
    private String overview;
    private String release_date;
    private String title;
    private String poster_path;
    private Boolean adult;
    private int id;
    private String original_language;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    public MovieData(String overview, String release_date, String title, String poster_path, Boolean adult, int id, String original_language, double popularity, int vote_count, boolean video, double vote_average) {
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.poster_path = poster_path;
        this.adult = adult;
        this.id = id;
        this.original_language = original_language;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;
    }

    protected MovieData(Parcel in) {
        overview = in.readString();
        release_date = in.readString();
        title = in.readString();
        poster_path = in.readString();
        id = in.readInt();
        original_language = in.readString();
        popularity = in.readDouble();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
    }

    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeInt(id);
        dest.writeString(original_language);
        dest.writeDouble(popularity);
        dest.writeInt(vote_count);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(vote_average);
    }
}
