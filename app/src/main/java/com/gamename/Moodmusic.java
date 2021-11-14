package com.gamename;

import android.content.Context;
import android.media.MediaPlayer;

public class Moodmusic {
    private static MediaPlayer mediaplayer = null;

    public Moodmusic(Context context) {
        startPlaying(context);
    }

    public void startPlaying(Context context){
        mediaplayer = MediaPlayer.create(context, R.raw.fishsong);
        mediaplayer.start(); // no need to call prepare(); create() does that for you
        mediaplayer.setLooping(true);
    }

    public void stopPlaying(){
        if (mediaplayer != null) {
            mediaplayer.stop();
            mediaplayer.release();
            mediaplayer = null;
        }
    }

    public void pausePlaying(Context context){
        if (mediaplayer != null && mediaplayer.isPlaying())
            stopPlaying();
        else
            startPlaying(context);
    }

    protected void onDestroy() {
        mediaplayer.release();
        mediaplayer = null;
    }
}