package com.jenkins.newworld.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jenkins.newworld.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LivePlayActivity extends AppCompatActivity implements View.OnClickListener {
    private String path = "";
    private VideoView mVideoView;
    private EditText mEditText;
    private Button mStartBtn;
    private Button mStopBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_play);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        //Vitamio.initialize(getApplicationContext());
        mEditText = (EditText) findViewById(R.id.url);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mStartBtn = (Button) findViewById(R.id.start);
        mStopBtn = (Button) findViewById(R.id.stop);

        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);

        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                path = mEditText.getText().toString();
                if (!TextUtils.isEmpty(path)) {
                    mVideoView.setVideoPath(path);
                    mVideoView.start();

                }
                break;
            case R.id.stop:
                mVideoView.stopPlayback();
                break;
        }
    }


}
