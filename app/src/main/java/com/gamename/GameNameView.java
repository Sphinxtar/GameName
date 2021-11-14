package com.gamename;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Color;
import android.graphics.Paint;

public class GameNameView extends SurfaceView implements SurfaceHolder.Callback {
   public final GameNameThread thread;
   public final Racket racket;
   public final Moodmusic moodmusic;
   private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
   private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    public GameNameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        moodmusic = new Moodmusic(context);
        racket = new Racket(context);
        thread = new GameNameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        racket.play(0);
        moodmusic.pausePlaying(getContext());
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
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        Rect r = new Rect(30, 30, 320, 240);
        super.draw(canvas);
        if(canvas != null) {
            canvas.drawRGB(0, 100, 205);
            canvas.drawRect(r, paint);
        }
    }

    public void update() {
    }
}
