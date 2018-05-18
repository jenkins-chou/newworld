package com.jenkins.newworld.activity;

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
import com.jenkins.newworld.model.mv.Mv;
import com.jenkins.newworld.model.video.Comment;
import com.jenkins.newworld.model.video.Video;
import com.jenkins.newworld.ui.StaticListView;
import com.jenkins.newworld.util.CommonWindowUtil;
import com.jenkins.newworld.util.DataUtil;
import com.xiao.nicevideoplayer.Clarity;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoPlayActivity extends AppCompatActivity {

    //data
    private String imageUrl;
    private String videoUrl;
    private NiceVideoPlayer mNiceVideoPlayer;
    @BindView(R.id.comment_listview)
    StaticListView comment_listview;
    @BindView(R.id.video_name)
    TextView video_name;//视频名称
    @BindView(R.id.video_author)
    TextView video_author;//视频author
    @BindView(R.id.video_count)
    TextView video_count;//视频观看次数

    @OnClick(R.id.img_back)
    void img_back(){
        finish();
    }@Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字亮色
        CommonWindowUtil.FlymeSetStatusBarLightMode(this.getWindow(),false);
        //设置状态栏文字亮色
        CommonWindowUtil.setLightStatusBar(this.getWindow());
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        //caculateLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String model = getIntent().getStringExtra("mv");
        if (model!=null&&!model.equals("")){
            Mv mv = new Gson().fromJson(model,Mv.class);
                System.out.println("----------mv");
                videoUrl = mv.getMv_url();
                imageUrl = mv.getMv_image();
            initMvInfo(mv);
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
                .placeholder(R.drawable.img_default)
                .crossFade()
                .into(controller.imageView());
        mNiceVideoPlayer.setController(controller);
        mNiceVideoPlayer.setUp(videoUrl,null);
}

    public List<Clarity> getClarites() {
        List<Clarity> clarities = new ArrayList<>();
        clarities.add(new Clarity("标清", "270P", "http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new Clarity("高清", "480P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new Clarity("超清", "720P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new Clarity("蓝光", "1080P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        return clarities;
    }

    public void initCommentList(){
        ListViewAdapter<Video> adapter = new ListViewAdapter<Video>(getComments(),R.layout.activity_video_play_comment_item) {
            @Override
            public void bindView(ViewHolder holder, Video obj) {
                holder.setImage(R.id.video_image,obj.getImageUrl());
                holder.setText(R.id.video_name,obj.getTitle());
            }
        };
        comment_listview.setAdapter(adapter);
        comment_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(VideoPlayActivity.this, "position :"+i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public ArrayList<Video> getComments(){
        ArrayList<Video> videos = new ArrayList<>();
        videos = (ArrayList) DataUtil.getVideoListData();
        return videos;
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
}
