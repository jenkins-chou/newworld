package com.jenkins.newworld.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.model.video.Video;
import com.xiao.nicevideoplayer.NiceVideoPlayer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeContentAdapter extends RecyclerView.Adapter<TypeContentAdapter.TypeLineHolder>{

    private Context mContext;

    private List<Video> videos;

    private LayoutInflater inflater;


    public TypeContentAdapter(Context mContext, List<Video> videos) {
        this.mContext = mContext;
        this.videos = videos;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeLineHolder(inflater.inflate(R.layout.frag_search_videopage_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
//        FragShareLineModel contentBean = fragShareLineModels.get(position);
            //holder.nice_video_player.setText("#" + contentBean.getTitle());
//        Glide.with(mContext)
//                .load(contentBean.getImageUrls())
//                .error(R.mipmap.avatar)
//                .into(holder.item_imageView);
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
    }

    public class TypeLineHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.item_title)
//        TextView item_title;
        @BindView(R.id.nice_video_player)
        NiceVideoPlayer nice_video_player;

        public TypeLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
