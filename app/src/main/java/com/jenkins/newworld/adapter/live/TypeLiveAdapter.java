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
import com.jenkins.newworld.model.live.LiveModel;
import com.jenkins.newworld.model.video.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeLiveAdapter extends RecyclerView.Adapter<TypeLiveAdapter.TypeLineHolder> implements View.OnClickListener {

    private Context mContext;

    private ArrayList<LiveModel> liveModels;

    private LayoutInflater inflater;

    //listener
    private OnItemClickListener onItemClickListener;

    public TypeLiveAdapter(Context mContext, ArrayList<LiveModel> liveModels) {
        this.mContext = mContext;
        this.liveModels = liveModels;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.frag_main_live_item, parent, false);
        view.setOnClickListener(this);
        return new TypeLineHolder(view);
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        LiveModel liveModel = liveModels.get(position);
        holder.item_title.setText("" + liveModel.getLive_name());
        holder.getView().setTag(position);
        Glide.with(mContext)
                .load(R.mipmap.frag_live_start_bg)
                .error(R.mipmap.frag_live_start_bg)
                .into(holder.item_imageView);
    }

    @Override
    public int getItemCount() {
        return liveModels == null ? 0 : liveModels.size();
    }

    public void setLiveModels(ArrayList<LiveModel> liveModels){
        this.liveModels = liveModels;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void clear() {
        if (liveModels!=null){
            liveModels.clear();
            notifyDataSetChanged();
        }
    }

    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_image)
        ImageView item_imageView;
        private View view;
        public TypeLineHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
        public View getView(){
            return view;
        }
    }

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

}
