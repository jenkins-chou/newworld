package com.jenkins.newworld.adapter.homepage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.CategoryActivity;
import com.jenkins.newworld.activity.RecommandActivity;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.model.video.Video;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/18.
 */

public class VideoRvAdapter extends RecyclerView.Adapter {

    private View header;
    private ArrayList<Video> videos;
    private int count;//item数量
    private Context context;
    private int headerType = 0;
    private int contentType = 1;
    private LayoutInflater inflater;
    private ArrayList<Mv> mvs;
    VideoContentAdapter adapter;
    public VideoRvAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);

        mvs = new ArrayList<>();
        adapter = new VideoContentAdapter(context,mvs);
        addHeader();
        addVideoModels();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == headerType){
            //头部菜单
            View view = inflater.inflate(R.layout.frag_main_video_videopage_header, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeHeaderHolder(view);
        }else if(viewType == contentType){
            View view = inflater.inflate(R.layout.frag_main_video_videopage_content, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeContentHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            TypeHeaderHolder typeHeaderHolder = (TypeHeaderHolder)holder;
            initHeader(typeHeaderHolder);
        }else if (position==1){
            TypeContentHolder typeContentHolder = (TypeContentHolder)holder;
            typeContentHolder.rvtype.setLayoutManager(new GridLayoutManager(context,1));
            typeContentHolder.rvtype.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0){
            return headerType;
        }else if (position == contentType){
            return contentType;
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //如果快速滑动， 不加载图片
                if (newState == 2) {
                    Glide.with(context).pauseRequests();
                } else {
                    Glide.with(context).resumeRequests();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    public void initHeader(TypeHeaderHolder holder){
        View view = holder.header;//头部视图
        ImageView topbar_gif = (ImageView)view.findViewById(R.id.topbar_gif);
        if (topbar_gif==null)
            return;
        AnimationDrawable animationDrawable = (AnimationDrawable)topbar_gif.getBackground();
        animationDrawable.start();

        LinearLayout allsort_btn = (LinearLayout)view.findViewById(R.id.allsort_btn);
        LinearLayout recommand_btn = (LinearLayout)view.findViewById(R.id.recommand_btn);

        allsort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryActivity.class);
                context.startActivity(intent);
            }
        });
        recommand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecommandActivity.class);
                context.startActivity(intent);
            }
        });

    }

    public void addHeader(){
        count ++;
        notifyDataSetChanged();
    }

    public void updateMv(ArrayList<Mv> mvs){
        //System.out.print("updateMv");
        if (adapter!=null){
            //System.out.print("进入到该方法");
            adapter.addData(mvs);
        }
        notifyDataSetChanged();
    }

    //添加单列显示的数据
    public void addVideoModels(){
        count ++;
        notifyDataSetChanged();
    }

    public class TypeHeaderHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.frag_share_banner_bar)
        View header;
        public TypeHeaderHolder(View view) {
            super(view);
            header = view;
            ButterKnife.bind(this, view);
        }
    }
    //单列显示holder
    public class TypeContentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content_list)
        RecyclerView rvtype;
        public TypeContentHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
