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

import com.bumptech.glide.Glide;
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
    private final int Banner_mode = 0;//banner轮播图
    private final int Category_mode = 1;//分类菜单
    private final int Line_mode = 2;//多列显示模式
    private int SingleLine_mode = 3;//单列显示模式
    private int count = 0;
    private String [] textArrays = new String[]{"this is content No.1","this is content No.2","this is content No.3"};

    TypeLineAdapter lineAdapter;//分列显示adapter
    TypeSingleLineAdapter singlelineAdapter;//单列显示adapter

    private ArrayList<FragShareLineModel> sindleLineModels;

    public RecyclerViewAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        sindleLineModels = new ArrayList<>();
        lineAdapter = new TypeLineAdapter(context);//单列显示的adapter
        singlelineAdapter= new TypeSingleLineAdapter(context);//单列显示adapter

        setBannerImages(getBanners());//初始化轮播图
        setCategorys(textArrays);//初始化分类菜单
        setFragShareLineModels(sindleLineModels);//多列显示
        setSingleLineModels(sindleLineModels);//单列显示

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
            //多列显示
            View view = inflater.inflate(R.layout.frag_main_share_recyclerview_line, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeLineHolder(view);
        }else if(viewType==SingleLine_mode){
            //单列显示
            View view = inflater.inflate(R.layout.frag_main_share_recyclerview_singleline, parent, false);
            StaggeredGridLayoutManager.LayoutParams params2 =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            params2.setFullSpan(true);
            view.setLayoutParams(params2);
            return new TypeSingleLineHolder(view);
        }
        else{
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
            holder1.rvtype.setAdapter(lineAdapter);
        }else if(position==SingleLine_mode){
            TypeSingleLineHolder holder1 = (TypeSingleLineHolder)holder;
            holder1.rvtype.setLayoutManager(new GridLayoutManager(context, 2));
            holder1.rvtype.setAdapter(singlelineAdapter);
        }
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
        }else if(position ==3){
            return SingleLine_mode;
        }
        else {
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

    //添加设置分列显示的数据集
    public void setFragShareLineModels(ArrayList<FragShareLineModel> fragShareLineModels){
        this.fragShareLineModels = fragShareLineModels;
        count ++;
        notifyDataSetChanged();
    }

    //添加单列显示的数据集
    public void setSingleLineModels(ArrayList<FragShareLineModel> sindleLineModels){
        this.sindleLineModels = sindleLineModels;
        count ++;
        notifyDataSetChanged();
    }

    //添加分类菜单
    public void setCategorys(String[] arrays){
        textArray = arrays;
        count ++;
        notifyDataSetChanged();
    }

    //刷新分列显示的数据
    public void updateLineData(ArrayList<FragShareLineModel> fragShareLineModels){
        lineAdapter.addData(fragShareLineModels);
    }

    public void updateSingleLineData(ArrayList<FragShareLineModel> fragShareLineModels){
        singlelineAdapter.addData(fragShareLineModels);
    }

    //添加轮播图的数据集
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

    //单列显示holder
    public class TypeSingleLineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.frag_share_recyclerview_singleline)
        RecyclerView rvtype;
        public TypeSingleLineHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //获取轮播图图片
    public ArrayList<String> getBanners(){
        ArrayList<String> result = new ArrayList<>();
        result.add(new String("http://i1.17173cdn.com/2fhnvk/YWxqaGBf/cms3/tRmNBdblckzgleB.jpg"));
        result.add(new String("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2430902877,3253634722&fm=11&gp=0.jpg"));
        result.add(new String("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524119767644&di=7e4d49e2a0fe62cf2970a2d70f5f866c&imgtype=0&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F55%2F07%2F16pic_5507165_b.jpg"));
        result.add(new String("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524119802221&di=462774704dedd46f548d22eb5c102966&imgtype=0&src=http%3A%2F%2Fp11.aipai.com%2Fphoto%2F980%2F24057980%2Fphoto%2F72%2F2779208%2F2779208_normal.jpg"));
        result.add(new String("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524119823918&di=4acb671e216d3db38a7b6fd4d2c201b6&imgtype=0&src=http%3A%2F%2Fimg.3dmgame.com%2Fuploads%2Fallimg%2F170516%2F316-1F5161K137.jpg"));
        return result;
    }

}
