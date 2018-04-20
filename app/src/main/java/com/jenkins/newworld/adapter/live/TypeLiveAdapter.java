package com.jenkins.newworld.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.model.video.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeLiveAdapter extends RecyclerView.Adapter<TypeLiveAdapter.TypeLineHolder> implements RecyclerView.OnItemTouchListener{

    private Context mContext;

    private List<Video> Videos;

    private LayoutInflater inflater;


    public TypeLiveAdapter(Context mContext, List<Video> Videos) {
        this.mContext = mContext;
        this.Videos = Videos;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeLineHolder(inflater.inflate(R.layout.frag_main_live_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        Video contentBean = Videos.get(position);
        holder.item_title.setText("" + contentBean.getTitle());
        Glide.with(mContext)
                .load(contentBean.getImageUrl())
                .error(R.mipmap.avatar)
                .into(holder.item_imageView);
    }

    @Override
    public int getItemCount() {
        return Videos == null ? 0 : Videos.size();
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

    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_image)
        ImageView item_imageView;

        public TypeLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
