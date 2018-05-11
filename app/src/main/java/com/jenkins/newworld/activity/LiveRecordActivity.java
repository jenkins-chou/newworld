package com.jenkins.newworld.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.faucamp.simplertmp.RtmpHandler;
import com.jenkins.newworld.R;
import com.jenkins.newworld.adapter.common.ListViewAdapter;
import com.jenkins.newworld.model.live.MirrorModel;
import com.jenkins.newworld.ui.HorizontalListView;
import com.jenkins.newworld.util.LiveTipDialog;
import com.seu.magicfilter.utils.MagicFilterType;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class LiveRecordActivity extends AppCompatActivity implements SrsEncodeHandler.SrsEncodeListener, RtmpHandler.RtmpListener, SrsRecordHandler.SrsRecordListener{

    //view
    @BindView(R.id.url)
    EditText mRempUrlEt;//获取url的输入框
    @BindView(R.id.live_record_bianma)
    ImageView live_record_bianma;
    @BindView(R.id.live_record_start)
    ImageView live_record_start;
    @BindView(R.id.mirror_listview)
    HorizontalListView mirror_listview;

    private boolean isEditLiveInfo = false;//判断是否已经填写直播间信息。
    //data
    private static final String TAG = "CameraActivity";
    private boolean isStart = false;
    private SrsPublisher mPublisher;
    private int encoding = 0;//0为硬编码 ，1为软编码
    private String rtmpUrl;

    //权限代码
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_live_record);
        ButterKnife.bind(this);
        initMirrorListView();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //没有权限
            autoObtainCameraPermission();
        }else{
            //有权限
            initmPublisher();
        }
    }

    public void initmPublisher(){
        mPublisher = new SrsPublisher((SrsCameraView) findViewById(R.id.glsurfaceview_camera));
        //编码状态回调
        mPublisher.setEncodeHandler(new SrsEncodeHandler(this));
        mPublisher.setRecordHandler(new SrsRecordHandler(this));
        //rtmp推流状态回调
        mPublisher.setRtmpHandler(new RtmpHandler(this));
        //预览分辨率
        mPublisher.setPreviewResolution(1280, 720);
        //推流分辨率
        mPublisher.setOutputResolution(720, 1280);
        //传输率
        mPublisher.setVideoHDMode();
        //开启美颜（其他滤镜效果在MagicFilterType中查看）
        //mPublisher.switchCameraFilter(MagicFilterType.WARM);
        //打开摄像头，开始预览（未推流）
        mPublisher.startCamera();
        mPublisher.switchCameraFace((mPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
    }

    //初始化滤镜列表
    public void initMirrorListView(){
        final ArrayList<MirrorModel> mirrorDatas = new ArrayList<MirrorModel>();
        mirrorDatas.add(new MirrorModel("美肤",R.mipmap.mirror,MagicFilterType.BEAUTY));
        mirrorDatas.add(new MirrorModel("健康",R.mipmap.mirror,MagicFilterType.HEALTHY));
        mirrorDatas.add(new MirrorModel("酷",R.mipmap.mirror,MagicFilterType.COOL));
        mirrorDatas.add(new MirrorModel("浪漫",R.mipmap.mirror,MagicFilterType.ROMANCE));
        mirrorDatas.add(new MirrorModel("樱花",R.mipmap.mirror,MagicFilterType.SAKURA));
        mirrorDatas.add(new MirrorModel("暖光",R.mipmap.mirror,MagicFilterType.WARM));
        mirrorDatas.add(new MirrorModel("复古",R.mipmap.mirror,MagicFilterType.NOSTALGIA));
        mirrorDatas.add(new MirrorModel("怀旧",R.mipmap.mirror,MagicFilterType.COOL));
        mirrorDatas.add(new MirrorModel("丰饶",R.mipmap.mirror,MagicFilterType.CALM));
        mirrorDatas.add(new MirrorModel("拿铁风",R.mipmap.mirror,MagicFilterType.LATTE));
        mirrorDatas.add(new MirrorModel("温柔",R.mipmap.mirror,MagicFilterType.TENDER));
        ListViewAdapter<MirrorModel> adapter = new ListViewAdapter<MirrorModel>(mirrorDatas,R.layout.activity_live_record_mirror_item) {
            @Override
            public void bindView(ViewHolder holder, MirrorModel obj) {
                holder.setImage(R.id.image,obj.getImg());
                holder.setText(R.id.name,obj.getName());
            }
        };
        mirror_listview.setAdapter(adapter);
        mirror_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mPublisher!=null){
                    mPublisher.switchCameraFilter(mirrorDatas.get(position).getType());
                }
                mirror_listview.setVisibility(View.GONE);
            }
        });
    }

    //开始直播按钮
    @OnClick(R.id.live_record_start)void activity_record_start(){
        if (isEditLiveInfo){
            if (isStart == false){
                isStart=true;//设定当前为播放状态
                live_record_bianma.setEnabled(false);//禁用编码切换按钮
                rtmpUrl = mRempUrlEt.getText().toString();
                if (TextUtils.isEmpty(rtmpUrl)) {
                    Toast.makeText(getApplicationContext(), "地址不能为空！", Toast.LENGTH_SHORT).show();
                }
                mPublisher.startPublish(rtmpUrl);
                mPublisher.startCamera();
                live_record_start.setImageResource(R.mipmap.live_record_stop);
            }else{
                isStart=false;//设定当前为停止状态
                live_record_bianma.setEnabled(true);//使能编码切换按钮
                mPublisher.stopPublish();
                mPublisher.stopRecord();
                live_record_start.setImageResource(R.mipmap.live_record_start);
            }
        }else{
            new LiveTipDialog(this, R.style.login_tip_dialog).show();
            isEditLiveInfo = true;
        }
    }

    //摄像头切换按钮
    @OnClick(R.id.live_record_close)void live_record_close(){
        this.finish();
    }
    //摄像头切换按钮
    @OnClick(R.id.live_record_camera)void activity_record_exchange_camera(){
        mPublisher.switchCameraFace((mPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
    }
    //声音按钮
    @OnClick(R.id.live_record_music)void live_record_music(){

    }
    //滤镜切换按钮
    @OnClick(R.id.live_record_mirror)void activity_record_mirror_exchange(){
        if (mirror_listview!=null){
            if (mirror_listview.getVisibility()==View.VISIBLE){
                mirror_listview.setVisibility(View.GONE);
            }else{
                mirror_listview.setVisibility(View.VISIBLE);
            }

        }
    }
    //编码方式切换
    @OnClick(R.id.live_record_bianma)void activity_record_code_style(){
        Toast.makeText(this, "编码按钮", Toast.LENGTH_SHORT).show();
        if (encoding == 0){
            encoding = 1;
            //切换为软编码方式
            Toast.makeText(this, "切换为软编码方式", Toast.LENGTH_SHORT).show();
            mPublisher.switchToSoftEncoder();
        }else{
            encoding = 0;
            //切换为硬编码方式
            Toast.makeText(this, "切换为硬编码方式", Toast.LENGTH_SHORT).show();
            mPublisher.switchToHardEncoder();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (mPublisher!=null)
        mPublisher.resumeRecord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPublisher!=null)
            mPublisher.pauseRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPublisher!=null){
            mPublisher.stopPublish();
            mPublisher.stopRecord();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mPublisher!=null){
            mPublisher.stopEncode();
            mPublisher.stopRecord();
            mPublisher.setScreenOrientation(newConfig.orientation);
            if (isStart ==true){
                mPublisher.startEncode();
            }
            mPublisher.startCamera();
        }
    }

    @Override
    public void onNetworkWeak() {
        Toast.makeText(getApplicationContext(), "网络型号弱", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkResume() {

    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    private void handleException(Exception e) {
        try {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            mPublisher.stopPublish();
            mPublisher.stopRecord();
        } catch (Exception e1) {
            //
        }
    }

    @Override
    public void onRtmpConnecting(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpConnected(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoStreaming() {

    }

    @Override
    public void onRtmpAudioStreaming() {

    }

    @Override
    public void onRtmpStopped() {
        Toast.makeText(getApplicationContext(), "已停止", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpDisconnected() {
        Toast.makeText(getApplicationContext(), "未连接服务器", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {

    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpSocketException(SocketException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {
        handleException(e);
    }

    @Override
    public void onRecordPause() {
        Toast.makeText(getApplicationContext(), "Record paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordResume() {
        Toast.makeText(getApplicationContext(), "Record resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordStarted(String msg) {
        Toast.makeText(getApplicationContext(), "Recording file: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordFinished(String msg) {
        Toast.makeText(getApplicationContext(), "MP4 file saved: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {
        Toast.makeText(this, "开始获取权限", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //ToastUtils.showShort(this, "您已经拒绝过一次");
                Toast.makeText(this, "您已经拒绝过一次", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO}, CAMERA_PERMISSIONS_REQUEST_CODE);
        }else{
            Toast.makeText(this, "已经获取过权限啦，亲", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已经获取到权限", Toast.LENGTH_SHORT).show();
                    initmPublisher();
                } else {
                    Toast.makeText(this, "请允许打开相机", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
