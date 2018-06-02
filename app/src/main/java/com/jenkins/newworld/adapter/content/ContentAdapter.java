package com.jenkins.newworld.adapter.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.model.live.LiveModel;
import com.jenkins.newworld.model.mv.Mv;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.TypeLineHolder> implements View.OnClickListener {

    private Context mContext;

    private ArrayList<Mv> mvs;

    private LayoutInflater inflater;

    //listener
    private OnItemClickListener onItemClickListener;

    public ContentAdapter(Context mContext, ArrayList<Mv> mvs) {
        this.mContext = mContext;
        this.mvs = mvs;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.frag_main_live_item, parent, false);
        view.setOnClickListener(this);
        return new TypeLineHolder(view);
    }

    public void addData(ArrayList<Mv> mvs){
        if (mvs!=null){
            this.mvs = mvs;
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        Mv mv = mvs.get(position);
        holder.item_title.setText("" + mv.getMv_name());
        holder.time.setText(""+mv.getMv_count()+"播放");
        holder.getView().setTag(position);
        Glide.with(mContext)
                .load(mv.getMv_image())
                .placeholder(R.mipmap.frag_live_start_bg)
                .error(R.mipmap.frag_live_start_bg)
                .into(holder.item_imageView);
    }

    @Override
    public int getItemCount() {
        return mvs == null ? 0 : mvs.size();
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
        if (mvs!=null){
            mvs.clear();
            notifyDataSetChanged();
        }
    }

    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.time)
        TextView time;
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
