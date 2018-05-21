package com.jenkins.newworld.adapter.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.VideoPlayActivity;
import com.jenkins.newworld.adapter.video.holder.VideoViewHolder;
import com.jenkins.newworld.model.video.Video;
import com.jenkins.newworld.util.CommonStringUtil;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.List;

/**
 * Created by XiaoJianjun on 2017/5/21.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private Context mContext;
    private List<Video> mVideoList;
    private View convertView;
    public VideoAdapter(Context context, List<Video> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_main_video_item, parent, false);
        VideoViewHolder holder = new VideoViewHolder(itemView);
        TxVideoPlayerController controller = new TxVideoPlayerController(mContext);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, final int position) {
        Video video = mVideoList.get(position);
        holder.bindData(video);
        //
        holder.setText(R.id.user_name, CommonStringUtil.subString(video.getTitle(),10));
        holder.setImage(R.id.user_avatar,video.getImageUrl());


        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "position:"+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
            }
        });

        ImageView user_avatar = (ImageView)holder.getItemView().findViewById(R.id.user_avatar);
        user_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "user_avatar:", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

}
