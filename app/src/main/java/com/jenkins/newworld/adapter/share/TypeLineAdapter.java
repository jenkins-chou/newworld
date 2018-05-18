package com.jenkins.newworld.adapter.share;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;
import com.jenkins.newworld.model.frag.FragShareLineModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeLineAdapter extends RecyclerView.Adapter<TypeLineAdapter.TypeLineHolder> implements RecyclerView.OnItemTouchListener{

    private Context mContext;

    private List<FragShareLineModel> fragShareLineModels;

    private LayoutInflater inflater;


    public TypeLineAdapter(Context mContext) {
        this.mContext = mContext;
        this.fragShareLineModels = new ArrayList<FragShareLineModel>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeLineHolder(inflater.inflate(R.layout.frag_main_share_recyclerview_line_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        FragShareLineModel contentBean = fragShareLineModels.get(position);
        holder.item_title.setText("#" + contentBean.getTitle());
        Glide.with(mContext)
                .load(contentBean.getImageUrls())
                .error(R.mipmap.avatar)
                .into(holder.item_imageView);
    }

    @Override
    public int getItemCount() {
        return fragShareLineModels == null ? 0 : fragShareLineModels.size();
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
    public void addData(ArrayList<FragShareLineModel> fragShareLineModels) {
        if (fragShareLineModels!=null){
            for (int i = 0;i<fragShareLineModels.size();i++){
                this.fragShareLineModels.add(fragShareLineModels.get(i));
            }
        }
        notifyDataSetChanged();
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
