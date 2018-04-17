package com.jenkins.newworld.adapter.share;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jenkins.newworld.R;
import com.jenkins.newworld.loader.GlideImageLoader;
import com.jenkins.newworld.model.frag.FragShareLineModel;
import com.jenkins.newworld.ui.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouzhenjian on 2018/4/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> bannerImages;//轮播图urls
    private ArrayList<String> categorys;//分类菜单
    private String[] textArray;//上下滑动公告栏数据
    private ArrayList<FragShareLineModel> fragShareLineModels;//分两列显示的数据
    private final int Banner_mode = 0;
    private final int Category_mode = 1;
    private final int Line_mode = 2;
    private int count = 0;
    private int currentType = 0;

    public RecyclerViewAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==Banner_mode){
            //轮播图
            View view = inflater.inflate(R.layout.frag_main_share_content_banner, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeBannerHolder(view);
        }else if(viewType==Category_mode){
            //category
            View view = inflater.inflate(R.layout.frag_main_share_content_navbar, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeCategoryHolder(view);
        }else if(viewType==Line_mode){
            //中间head下面的布局
            View view = inflater.inflate(R.layout.frag_main_share_recyclerview_line, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeLineHolder(view);
        }else{
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("bind view position:",position+"");
        if (position == Banner_mode){
            TypeBannerHolder holder1 = (TypeBannerHolder)holder;
            initBanner(holder1,bannerImages);
        }else if(position == Category_mode){
            TypeCategoryHolder holder1 = (TypeCategoryHolder)holder;
            initCategory(holder1,textArray);
        }else if (position == Line_mode){
            TypeLineHolder holder1 = (TypeLineHolder)holder;
            holder1.rvtype.setLayoutManager(new GridLayoutManager(context, 2));
            TypeLineAdapter lineAdapter = new TypeLineAdapter(context, fragShareLineModels);
            holder1.rvtype.setAdapter(lineAdapter);
            holder1.rvtype.addOnItemTouchListener(lineAdapter);
        }
    }

    //初始化分类菜单
    public void initCategory(TypeCategoryHolder holder1, String[] textArray) {
        LinearLayout linearLayout = (LinearLayout)holder1.rvtype;
        MarqueeTextView marqueeTv = (MarqueeTextView)linearLayout.findViewById(R.id.marqueeTv);
        marqueeTv.setTextArraysAndClickListener(textArray, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Log.e("initCategory","initCategory");
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        //返回当前view type
        if (position==0){
            return Banner_mode;
        }else if(position==1){
            return Category_mode;
        }else if(position==2){
            return Line_mode;
        }else {
            return 0;
        }
    }

    //初始化轮播图banner
    public void initBanner(TypeBannerHolder holder,ArrayList<String> datas){
        Log.e("initBanner","initBanner");
        RelativeLayout relativeLayout = holder.rvtype;
        Banner banner = (Banner)relativeLayout.findViewById(R.id.banner);
        banner.setDelayTime(4000);
        banner.setImages(datas)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(context, "position:"+position, Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }

    //设置分列显示的数据集
    public void setFragShareLineModels(ArrayList<FragShareLineModel> fragShareLineModels){
        this.fragShareLineModels = fragShareLineModels;
        count ++;
        notifyDataSetChanged();
    }

    //设置分类菜单
    public void setCategorys(String[] arrays){
        textArray = arrays;
        count ++;
        notifyDataSetChanged();
    }

    //设置轮播图的数据集
    public void setBannerImages(ArrayList<String> bannerImages){
        this.bannerImages = bannerImages;
        //Log.e("setFragShareLineModels",fragShareLineModels.size()+"");
        count ++;
        notifyDataSetChanged();
    }

    //轮播图holder
    public class TypeBannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.frag_share_banner_bar)
        RelativeLayout rvtype;
        public TypeBannerHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    //菜单category
    public class TypeCategoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.frag_share_category)
        LinearLayout rvtype;
        public TypeCategoryHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    //分列显示holder
    public class TypeLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.frag_share_recyclerview_line)
        RecyclerView rvtype;
        public TypeLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
