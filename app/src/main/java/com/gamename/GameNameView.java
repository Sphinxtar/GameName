package com.gamename;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
/*
 import android.util.DisplayMetrics;
 import android.view.Display;
 import android.view.WindowManager;
 import android.util.Log;
 import android.graphics.Point;
*/
import androidx.annotation.NonNull;

public class GameNameView extends SurfaceView implements SurfaceHolder.Callback {
    public final GameNameThread thread;
    public final Racket racket;
    public final Moodmusic moodmusic;
    public final PlayingField pf;
    public Menus menu;
    public Slides slides;
    public int gstate = 3; // splash
    private Context ctext;
    // DisplayMetrics displayMetrics;

    public GameNameView(Context context) {
        super(context);
        setCtext(context);
        getHolder().addCallback(this);
        pf = new PlayingField();
        moodmusic = new Moodmusic(context);
        racket = new Racket(getCtext());
        thread = new GameNameThread(getHolder(), this);
        moodmusic.pausePlaying(getCtext());
        setFocusable(true);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        racket.play(0);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int newstate = 0;
        if (gstate == 0) {
            System.out.println(".");
            // playing the game
        } else if (gstate == 1) { // menu 1
            newstate = menu.hitButton(event);
        } else if (gstate > 2) { // slide
            newstate = slides.hitButton(gstate - 3);
        }
        performClick();
        if (gstate < 0 ) {
            thread.setRunning(false);
            System.exit(1);
        } else {
            gstate = newstate;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            if (pf.changed(canvas)) {
                pf.setScaleFactor(canvas);
                menu = new Menus( pf.getSidemargin(), pf.getTopmargin(),pf.getVportRight() - pf.getVportLeft(), pf.getVportBottom() - pf.getVportTop());
                slides = new Slides(getCtext(), pf.getVportRight() - pf.getVportLeft(), pf.getVportBottom() - pf.getVportTop());
            }
//        Log.i(TAG, "draw: " + high + " x " + wide);
            if (gstate == 0) { // PLAY THE GAME
                Paint p = new Paint();
                p.setColor(Color.RED);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(pf.getVportLeft(), pf.getVportTop(), pf.getVportRight(), pf.getVportBottom(), p);
            } else if (gstate > 0 && gstate <= 2) {
                menu.draw(canvas);
            } else if (gstate > 2) {
                slides.drawSlide(canvas, gstate - 3, pf.getVportLeft(), pf.getVportTop());
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
//        makeLevel();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    public void update() {
        // game logic here
    }

    public Context getCtext() {
        return ctext;
    }

    public void setCtext(Context ctext) {
        this.ctext = ctext;
    }
}
