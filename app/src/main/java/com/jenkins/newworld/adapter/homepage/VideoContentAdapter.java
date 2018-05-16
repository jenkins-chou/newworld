package com.jenkins.newworld.adapter.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.model.video.Video;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.List;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class VideoContentAdapter extends RecyclerView.Adapter<VideoContentAdapter.VideoViewHolder>{

    private Context mContext;

    private List<Video> videos;

    private LayoutInflater inflater;


    public VideoContentAdapter(Context mContext, List<Video> videos) {
        this.mContext = mContext;
        this.videos = videos;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item  = inflater.inflate(R.layout.frag_main_homepage_videopage_item, parent, false);
        VideoViewHolder holder = new VideoViewHolder(item);
        TxVideoPlayerController controller = new TxVideoPlayerController(mContext);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.bindData(video);

        ImageView user_avatar = (ImageView)holder.getItemView().findViewById(R.id.user_avatar);
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public TxVideoPlayerController mController;
        public NiceVideoPlayer mVideoPlayer;
        private View itemView;
        public VideoViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mVideoPlayer = (NiceVideoPlayer) itemView.findViewById(R.id.nice_video_player);
            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
            params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
            params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
            mVideoPlayer.setLayoutParams(params);
        }

        public void setController(TxVideoPlayerController controller) {
            mController = controller;
            mVideoPlayer.setController(mController);
        }

        public View getItemView() {
            return itemView;
        }

        public void bindData(Video video) {
            mController.setTitle(video.getTitle());
            mController.setLenght(video.getLength());
            Glide.with(itemView.getContext())
                    .load(video.getImageUrl())
                    .placeholder(R.mipmap.frag_live_start_bg)
                    .error(R.mipmap.frag_live_start_bg)
                    //.crossFade()
                    .into(mController.imageView());
            mVideoPlayer.setUp(video.getVideoUrl(), null);
        }

        public void setText(int id,String value){
            TextView textView = (TextView)itemView.findViewById(id);
            if (textView!=null){
                if (value==null){
                    value="value";
                }
                textView.setText(value);
            }
        }
        public void setImage(int id,String imageUrl){
            ImageView imageView = (ImageView)itemView.findViewById(id);
            if (imageView!=null){
                if (imageUrl==null){
                    imageUrl="";
                }
                Glide.with(itemView.getContext())
                        .load(imageUrl)
                        .placeholder(R.mipmap.avatar)
                        .error(R.mipmap.avatar)
                        .override(30,30)//裁剪图片
                        //.crossFade()
                        .into(imageView);
            }
        }
    }


}
