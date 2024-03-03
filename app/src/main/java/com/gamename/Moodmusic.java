package com.gamename;

import android.content.Context;
import android.media.MediaPlayer;

public class Moodmusic {
    private static MediaPlayer mediaplayer = null;

    public Moodmusic(Context context) {
        mediaplayer = MediaPlayer.create(context, R.raw.moodmusic);
        mediaplayer.setLooping(true);
        startPlaying();
    }

    public void startPlaying(){
        mediaplayer.start(); // no need to call prepare(); create() does that for you
    }

    public void stopPlaying(){
        if (mediaplayer != null) {
            mediaplayer.stop();
        }
    }

    public void pausePlaying(){
        if (mediaplayer != null && mediaplayer.isPlaying())
            mediaplayer.pause();
        else
            startPlaying();
    }

    protected void onDestroy() {
        mediaplayer.release();
        mediaplayer = null;
    }
}