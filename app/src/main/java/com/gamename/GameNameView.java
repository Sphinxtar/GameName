package com.gamename;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Color;
import android.graphics.Paint;

//import android.view.View;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Matrix;
//import android.view.MotionEvent;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;

public class GameNameView extends SurfaceView implements SurfaceHolder.Callback {
   public final GameNameThread thread;

    public GameNameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new GameNameThread(getHolder(), this);
        setFocusable(true);
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
