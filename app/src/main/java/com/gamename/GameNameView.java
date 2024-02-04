package com.gamename;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
 import android.util.Log;
import androidx.annotation.NonNull;

public class GameNameView extends SurfaceView implements SurfaceHolder.Callback {
    public final GameNameThread thread;
    public final Racket racket;
    public final Moodmusic moodmusic;
    public final PlayingField pf;
    public Sprite pix;
    public Dpad dpad;
    public Menus menu;
    public Slides slides;
    public Player player;
    public int gstate = 3; // splash
    private Context ctext;
    // DisplayMetrics displayMetrics;
//    private static final String TAG = "GAMENAMSTYLE";

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
    public boolean onTouchEvent(MotionEvent event) {
        int newstate = 0;
        if (gstate == 0) {  // playing the game
            if (dpad.hitButton(event) > 0)
                newstate = 1;
        } else if (gstate == 1) { // menu 1
            newstate = menu.hitButton(event);
            performClick();
        } else if (gstate > 2) { // slide
            newstate = slides.hitButton(gstate - 3);
            performClick();
        }
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
        Paint p = new Paint();
        if (canvas != null) {
            super.draw(canvas);
            if (pf.changed(canvas)) {
                pf.setScaleFactor(canvas);
                dpad = new Dpad(pf.getVport());
                menu = new Menus( pf.getSidemargin(), pf.getTopmargin() + 10,pf.getVportRight() - pf.getVportLeft(), pf.getVportBottom() - pf.getVportTop());
                slides = new Slides(getCtext(), pf.getVportRight() - pf.getVportLeft(), pf.getVportBottom() - pf.getVportTop());
                pix = new Sprite(getCtext(), pf);
                player = new Player(pf);
            }
            if (gstate == 0) { // PLAY THE GAME
                p.setColor(Color.RED);
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(4);
                canvas.drawRect(pf.getVportLeft(), pf.getVportTop(), pf.getVportRight(), pf.getVportBottom(), p);
                dpad.draw(canvas);
                pix.drawCenterSprite(canvas, player.sprite,player.spot.getX()+pf.getVportLeft(), player.spot.getY()+pf.getVportTop());
            } else if (gstate > 0 && gstate <= 2) {
                p.setColor(Color.YELLOW);
                p.setStyle(Paint.Style.FILL);
                canvas.drawRect(pf.getVportLeft(), pf.getVportTop(), pf.getVportRight(), pf.getVportBottom(), p);
                slides.drawSlide(canvas, 5, pf.getVportLeft(), pf.getVportTop());
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
