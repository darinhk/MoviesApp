package diyarme.com.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import diyarme.com.moviesapp.models.MovieData;

/**
 * Created by D.Kablaoui on 3/15/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<MovieData> movieData;
    PositionClickListener clicklistener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_movieimg;
        private TextView tv_title;
        private LoadingImage image;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);

            iv_movieimg = (ImageView) itemView.findViewById(R.id.iv_movieimg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicklistener.itemClicked(getAdapterPosition());
                }
            });


        }
    }

    public MyAdapter(Context context,ArrayList<MovieData> movieData) {
        this.context = context;
        this.movieData = movieData;
    }


    public void setCallBack(PositionClickListener listener) {
        clicklistener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public MovieData getItem(int position){
        return movieData.get(position);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(movieData.get(position).getTitle());
//        holder.image = new LoadingImage(holder.iv_movieimg);
//        holder.image.execute("https://image.tmdb.org/t/p/w500/" + movieData.get(position).getPoster_path());
        Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w500/" + movieData.get(position).getPoster_path())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(holder.iv_movieimg);

    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }


}
