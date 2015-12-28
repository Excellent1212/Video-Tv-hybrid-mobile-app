package com.hitherejoe.vineyard.ui.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

public class LoopingVideoView extends VideoView {

    private MediaPlayer mMediaPlayer;

    public LoopingVideoView(Context context) {
        super(context);
    }

    public LoopingVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoopingVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoopingVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setupMediaPlayer(String url, final OnVideoReadyListener onVideoReadyListener) {
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaPlayer = mp;
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setVolume(0, 0);
                mMediaPlayer.start();
                onVideoReadyListener.onVideoReady();
            }
        });
        setVideoURI(Uri.parse(url));
    }

    public void stopMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer = null;
        }
    }

    public interface OnVideoReadyListener {
        void onVideoReady();
    }

}
