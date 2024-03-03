package com.gamename;

import android.graphics.Canvas;
import android.graphics.Rect;
// import android.util.Log;


public class PlayingField {

    // canvas size
    public int canvasHigh;
    public int canvasWide;
    public int scalefactor;
    private int topmargin;
    private int sidemargin;
    // display port = canvas size
    public Rect dport;
    // view into dport
    public Rect vport;

    public PlayingField() {
        scalefactor = 1;
        canvasWide = 0;
        canvasHigh = 0;
        setSidemargin(0);
        setTopmargin(0);
        dport = new Rect();
        vport = new Rect();
    }

    public void setScaleFactor(Canvas canvas)
    {
//        String TAG = "PF INIT";
        canvasWide = canvas.getWidth();
        canvasHigh = canvas.getHeight();
        dport.left  = 0;
        dport.top = 0;
        dport.right = canvasWide;
        dport.bottom = canvasHigh;
        scalefactor = dport.bottom / 240;
        int wide = scalefactor * 320;
        int high = scalefactor * 240;
        setSidemargin((dport.right - wide) / 2);
        setTopmargin((dport.bottom - high) / 2);
        setVport(getSidemargin(), getTopmargin(), wide + getSidemargin(), high + getTopmargin());
//        Log.i(TAG, "dport: " + dport.toString() + " vport: " + vport.toString() );
    }

    public int getScaleFactor() {
        return scalefactor;
    }

    public void setVport(int left, int top, int right, int bottom) {
        vport.left = left;
        vport.top = top;
        vport.right = right;
        vport.bottom = bottom;
    }


    public boolean changed(Canvas canvas) {
        return !((canvasHigh == canvas.getHeight()) && (canvasWide == canvas.getWidth()));
    }

    public Rect getVport() {
        return vport;
    }
    public int getVportLeft() {
        return vport.left;
    }

    public int getVportTop() {
        return vport.top;
    }

    public int getVportRight() {
        return vport.right;
    }

    public int getVportBottom() {
        return vport.bottom;
    }

    public int getSidemargin() {
        return sidemargin;
    }

    public void setSidemargin(int sidemargin) {
        this.sidemargin = sidemargin;
    }

    public int getTopmargin() {
        return topmargin;
    }

    public void setTopmargin(int topmargin) {
        this.topmargin = topmargin;
    }
}
