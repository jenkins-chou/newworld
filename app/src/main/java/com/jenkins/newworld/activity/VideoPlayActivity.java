package com.jenkins.newworld.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextDirectionHeuristics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.common.ListViewAdapter;
import com.jenkins.newworld.contract.mv.MvContract;
import com.jenkins.newworld.model.base.ResultModel;
import com.jenkins.newworld.model.movie.Movie;
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.model.video.Comment;
import com.jenkins.newworld.model.video.Video;
import com.jenkins.newworld.presenter.mv.MvPresenter;
import com.jenkins.newworld.ui.StaticListView;
import com.jenkins.newworld.util.AccountUtil;
import com.jenkins.newworld.util.CommonDialog;
import com.jenkins.newworld.util.CommonWindowUtil;
import com.jenkins.newworld.util.DataUtil;
import com.xiao.nicevideoplayer.Clarity;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoPlayActivity extends AppCompatActivity implements MvContract.MView{

    //data
    private String imageUrl;
    private String videoUrl;
    private String mv_id;
    private NiceVideoPlayer mNiceVideoPlayer;
    private String video_type;
    private MvPresenter presenter;
    private ArrayList<Mv> mvs;
    private ListViewAdapter<Mv> adapter;
    private Context context;

    @BindView(R.id.comment_listview)
    StaticListView comment_listview;
    @BindView(R.id.video_name)
    TextView video_name;//视频名称
    @BindView(R.id.video_author)
    TextView video_author;//视频author
    @BindView(R.id.video_count)
    TextView video_count;//视频观看次数
    @OnClick(R.id.collect)
    void collect(){
        //Toast.makeText(context, "collect", Toast.LENGTH_SHORT).show();
        Map<String,Object> params = new HashMap<>();
        params.put("user_id", AccountUtil.getUserID(this));
        params.put("mv_id",mv_id);
        presenter.collectMv(params);
    }
    @OnClick(R.id.img_back)
    void img_back(){
        finish();
    }@Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字亮色
        CommonWindowUtil.SetStatusBarLightMode(this.getWindow(),false);
        //设置状态栏文字亮色
        CommonWindowUtil.setLightStatusBar(this.getWindow());
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
        context = this;
        //caculateLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String mvJsoon = getIntent().getStringExtra("mv");
        String movieJson = getIntent().getStringExtra("movie");
        if (mvJsoon!=null&&!mvJsoon.equals("")){
            Mv mv = new Gson().fromJson(mvJsoon,Mv.class);
            //System.out.println("----------mv");
            videoUrl = mv.getMv_url();
            imageUrl = mv.getMv_image();
            video_type = mv.getMv_type();
            mv_id = mv.getMv_id();
            initMvInfo(mv);
        }else if (movieJson!=null&&!movieJson.equals("")){
            Movie movie = new Gson().fromJson(movieJson,Movie.class);
            videoUrl = movie.getMovie_url();
            imageUrl = ""+movie.getMovie_image();
            initMovieInfo(movie);
        }
        initPlayer();//初始化播放器
        initCommentList();//初始化推荐列表
    }



    //初始化mv数据
    public void initMvInfo(Mv mv){
        if (mv!=null){
            video_name.setText(mv.getMv_name());
            video_author.setText("老了个周");
            video_count.setText(mv.getMv_count()+" 次播放");
        }
    }

    //初始化movie数据
    private void initMovieInfo(Movie movie) {
        if (movie!=null){
            video_name.setText(movie.getMovie_name());
            video_author.setText(movie.getMovie_actor()+"");
            video_count.setText(movie.getMovie_grade()+"分");
        }
    }

    private void initPlayer() {
        mNiceVideoPlayer = null;
        mNiceVideoPlayer = (NiceVideoPlayer) findViewById(R.id.nice_video_player);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // IjkPlayer or MediaPlayer
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("");
        controller.setLenght(117000);
        //controller.setClarity(getClarites(), 0);
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.mipmap.frag_live_start_bg)
                .error(R.mipmap.frag_live_start_bg)
                .crossFade()
                .into(controller.imageView());
        mNiceVideoPlayer.setController(controller);
        mNiceVideoPlayer.setUp(videoUrl,null);
        mNiceVideoPlayer.start();
}

    public void initCommentList(){
        presenter = new MvPresenter(this,this);
        mvs = new ArrayList<>();
        adapter = new ListViewAdapter<Mv>(mvs,R.layout.activity_video_play_comment_item) {
            @Override
            public void bindView(ViewHolder holder, Mv obj) {
                holder.setImage(R.id.video_image,obj.getMv_image());
                holder.setText(R.id.video_name,obj.getMv_name());
                holder.setText(R.id.video_tag,obj.getMv_count()+"播放");
            }
        };
        comment_listview.setAdapter(adapter);
        comment_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(VideoPlayActivity.this, "position :"+i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("mv",new Gson().toJson(mvs.get(i)));
                startActivity(intent);
                finish();
            }
        });
        Map<String,Object> params = new HashMap<>();
        params.put("type",video_type);
        params.put("start",0);
        presenter.getTypeMv(params);
    }

    public void enterTinyWindow(View view) {
        if (mNiceVideoPlayer.isIdle()) {
            Toast.makeText(this, "要点击播放后才能进入小窗口", Toast.LENGTH_SHORT).show();
        } else {
            mNiceVideoPlayer.enterTinyWindow();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void finish() {
        super.finish();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }

    @Override
    public void success(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel) object;
            ArrayList<Mv> mvs = (ArrayList<Mv>)resultModel.getData();
            this.mvs = mvs;
            adapter.addList(mvs);
            //adapter.addData(mvs);
        }
    }

    @Override
    public void failed(Object object) {

    }

    @Override
    public void completed(Object object) {

    }

    //收藏视频的返回结果
    @Override
    public void collectResult(Object object) {
        if (object!=null){
            ResultModel resultModel = (ResultModel)object;
            if (resultModel.getStatus().equals("200")){
                CommonDialog.showSuccessDialog(this,"","收藏成功");
            }else if (resultModel.getStatus().equals("201")){
                CommonDialog.showWarningDialog(this,"","收藏过了");
            }else{
                CommonDialog.showWarningDialog(this,"","收藏失败");
            }
        }
    }

    @Override
    public void getCollectMv(Object object) {

    }
}
