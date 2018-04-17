package com.jenkins.newworld.adapter.share;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jenkins.newworld.R;
import com.jenkins.newworld.model.frag.FragShareLineModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class TypeLineAdapter extends RecyclerView.Adapter<TypeLineAdapter.TypeLineHolder> {

    private Context mContext;

    private List<FragShareLineModel> fragShareLineModels;

    private LayoutInflater inflater;


    public TypeLineAdapter(Context mContext, List<FragShareLineModel> fragShareLineModels) {
        this.mContext = mContext;
        this.fragShareLineModels = fragShareLineModels;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypeLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeLineHolder(inflater.inflate(R.layout.frag_main_share_recyclerview_line_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeLineHolder holder, int position) {
        FragShareLineModel contentBean = fragShareLineModels.get(position);
        holder.homeReadTitle.setText("#" + contentBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return fragShareLineModels == null ? 0 : fragShareLineModels.size();
    }

    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView homeReadTitle;

        public TypeLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
