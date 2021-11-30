package com.gamename;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

public class GameNameView extends SurfaceView implements SurfaceHolder.Callback {
    public final GameNameThread thread;
    public final Racket racket;
    public final Moodmusic moodmusic;
    public Menus menu;
    public Slides slides;
    public int gstate = 2;
    private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public final float scaleFactor = screenHeight / 240;

    public GameNameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        menu = new Menus(screenHeight, screenWidth);
        slides = new Slides(context, scaleFactor);
        moodmusic = new Moodmusic(context);
        racket = new Racket(context);
        thread = new GameNameThread(getHolder(), this);
//        moodmusic.pausePlaying(getContext());
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
        if (gstate == 0 ) {
            // playing the game
        } else if (gstate > 0 && gstate < 2) { // it's a menu
            gstate = menu.hitButton(gstate - 1, event);
        } else if (gstate >= 2 && gstate < 6) { // it's a slide
            gstate = slides.hitButton(gstate - 2, event);
        }
        performClick();
        if (gstate == -1) {
            System.exit(0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            if (gstate == 0) { // PLAY THE GAME
                canvas.drawRGB(0, 100, 205);
            } else if (gstate > 0 && gstate < 2) {
                menu.draw(gstate - 1, canvas);
            } else if (gstate >= 2) {
                slides.drawSlide(canvas, gstate - 2, screenWidth, screenHeight);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        makeLevel();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
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
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void update() {
        // game logic here
    }
}
