package com.jenkins.newworld.adapter.share;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.activity.VideoPlayActivity;
import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.model.video.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeLineAdapter extends RecyclerView.Adapter<TypeLineAdapter.TypeLineHolder> implements RecyclerView.OnItemTouchListener{

    private Context mContext;

    private List<Mv> mvs;

    private LayoutInflater inflater;


    public TypeLineAdapter(Context mContext) {
        this.mContext = mContext;
        this.mvs = new ArrayList<Mv>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeLineHolder(inflater.inflate(R.layout.frag_main_homepage_recyclerview_line_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        final Mv mv = mvs.get(position);
        holder.item_title.setText("" + mv.getMv_name());
        holder.tag.setText(mv.getMv_count()+"次播放");
        Glide.with(mContext)
                .load(mv.getMv_image())
                .error(R.mipmap.avatar)
                .into(holder.item_imageView);
        RelativeLayout root_view = holder.root_view;
        root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("mv",new Gson().toJson(mv));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mvs == null ? 0 : mvs.size();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Toast.makeText(mContext, "dsadsadsa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    //添加数据
    public void addData(ArrayList<Mv> mvs) {
        if (mvs!=null){
            for (int i = 0;i<mvs.size();i++){
                this.mvs.add(mvs.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_image)
        ImageView item_imageView;
        @BindView(R.id.item_tag)
        TextView tag;

        @BindView(R.id.root_view)
        RelativeLayout root_view;

        public TypeLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
