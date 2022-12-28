package com.gamename;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.WindowMetrics;


public class PlayingField {

    // canvas size
    public int canvasHigh;
    public int canvasWide;
    public int scalefactor;
    // canvas edge to vport edge width
    public int topmargin;
    public int sidemargin;
    // game viewport
    public Rect vport;
    // display port
    public WindowMetrics dport;
    // location of viewport upper left corner in canvas
    public int top;
    public int left;
    // virtual playing field size
    public int high;
    public int wide;

    public PlayingField(Context context) {
        vport = new Rect();
        dport = ((Activity) context).getWindowManager().getMaximumWindowMetrics();
        canvasHigh = dport.getBounds().bottom;
        canvasWide = dport.getBounds().right;
        scalefactor = canvasHigh / 240;
        high = scalefactor * 240;
        wide = (scalefactor + 1) * 320;
        topmargin = (canvasHigh - high) / 2;
        sidemargin = (canvasWide - wide) / 2;
        top = topmargin;
        left = sidemargin;
        setVport(left, top, wide, high);
    }

    public void setVport(int left, int top, int right, int bottom) {
        this.vport.left = left;
        this.vport.top = top;
        this.vport.right = right;
        this.vport.bottom = bottom;
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

    public int getVportBottom() {
        return vport.bottom;
    }

    public int getVportRight() {
        return vport.right;
    }
}
