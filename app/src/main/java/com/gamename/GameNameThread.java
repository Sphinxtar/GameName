package com.gamename;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameNameThread extends Thread {
    private final GameNameView gamenameView;
    private final SurfaceHolder surfaceHolder;
    private boolean running;
    public static Canvas canvas;
    private double averageFPS;

    public GameNameThread(SurfaceHolder surfaceHolder, GameNameView myView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamenameView = myView;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        int targetFPS = 18; // it's 1980 again
        long targetTime = 1000/ targetFPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamenameView.update();
                    this.gamenameView.draw(canvas);
                }
            } catch (Exception ignored) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                sleep(waitTime);
            }catch(Exception ignored){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == targetFPS)
            {
                // averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                // System.out.println(averageFPS);
            }
        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }
}
