package com.jenkins.newworld.activity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.jenkins.newworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LivePlayActivity extends Activity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

    private static final String TAG = "MediaPlayerDemo";
    private int mVideoWidth;
    private int mVideoHeight;
    private MediaPlayer mMediaPlayer;
    private SurfaceView mPreview;
    private SurfaceHolder holder;
    private String path;
    private static final int STREAM_VIDEO = 5;
    private boolean mIsVideoSizeKnown = false;
    private boolean mIsVideoReadyToBePlayed = false;

    @OnClick(R.id.close)void close(){
        finish();
    }
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_live_play);
        ButterKnife.bind(this);

        mPreview = (SurfaceView) findViewById(R.id.surface);
        holder = mPreview.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.RGBA_8888);

    }

    private void playVideo() {
        doCleanUp();
        try {
            path="rtmp://139.199.205.207:1935/live/livestream";
            mMediaPlayer = new MediaPlayer(this);
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.setDisplay(holder);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnVideoSizeChangedListener(this);
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
        }
    }
    public void onBufferingUpdate(MediaPlayer arg0, int percent) {

    }

    public void onCompletion(MediaPlayer arg0) {
        Log.d(TAG, "onCompletion called");
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.v(TAG, "onVideoSizeChanged called");
        if (width == 0 || height == 0) {
            Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
            return;
        }
        mIsVideoSizeKnown = true;
        mVideoWidth = width;
        mVideoHeight = height;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void onPrepared(MediaPlayer mediaplayer) {
        Log.d(TAG, "onPrepared called");
        mIsVideoReadyToBePlayed = true;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
        Log.d(TAG, "surfaceChanged called");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder) {
        Log.d(TAG, "surfaceDestroyed called");
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated called");
        playVideo();

    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void doCleanUp() {
        mVideoWidth = 0;
        mVideoHeight = 0;
        mIsVideoReadyToBePlayed = false;
        mIsVideoSizeKnown = false;
    }

    private void startVideoPlayback() {
        Log.v(TAG, "startVideoPlayback");
        holder.setFixedSize(mVideoWidth, mVideoHeight);
        mMediaPlayer.start();
    }
}

