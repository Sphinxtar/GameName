package com.gamename;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameNameView extends SurfaceView implements SurfaceHolder.Callback {
    public final GameNameThread thread;
    public final Racket racket;
    public final Moodmusic moodmusic;
    public Menus menu;
    public Slides slides;
    public int gstate = 1;
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
        setFocusable(true);
    }
    @Override
    public boolean performClick() {
        super.performClick();
        racket.play(0);
//        moodmusic.pausePlaying(getContext());
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (gstate > 0 ) {
            gstate = menu.hitButton(gstate, event);
        }
        performClick();
        return super.onTouchEvent(event);
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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (gstate == 1) {
            menu.draw(gstate, canvas);
        } else if(gstate == 2) {
            slides.drawSlide(canvas, 0, screenWidth );
            // canvas.drawBitmap(mybitmap, 0, 0, Paint paint);
        } else {
            canvas.drawRGB(0, 100, 205);
        }
    }

    public void update() {
    }
}
