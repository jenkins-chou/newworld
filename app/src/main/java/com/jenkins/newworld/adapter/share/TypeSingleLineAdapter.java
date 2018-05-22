package com.jenkins.newworld.adapter.share;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.VideoPlayActivity;
import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.model.movie.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeSingleLineAdapter extends RecyclerView.Adapter<TypeSingleLineAdapter.TypeLineHolder>{

    private Context mContext;

    private ArrayList<Movie> movies;

    private LayoutInflater inflater;


    public TypeSingleLineAdapter(Context mContext) {
        this.mContext = mContext;
        this.movies = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeLineHolder(inflater.inflate(R.layout.frag_main_homepage_recyclerview_singleline_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        final Movie movie= movies.get(position);
        holder.item_title.setText("" + movie.getMovie_name());
        Glide.with(mContext)
                .load("http://"+movie.getMovie_image())
                .error(R.mipmap.avatar)
                .into(holder.item_imageView);
        holder.item_view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("movie",new Gson().toJson(movie));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void addData(ArrayList<Movie> movies) {
        if (movies!=null){
            for (int i = 0;i<movies.size();i++){
                this.movies.add(movies.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_image)
        ImageView item_imageView;
        @BindView(R.id.item_view)
        RelativeLayout item_view;


        public TypeLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
