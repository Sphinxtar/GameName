package com.gamename;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Slides {

    public int wide;
    public int high;
    public slide[] slides = new slide[4];

    public Slides(Context context, float scale) {
        wide = (int) (320 * scale);
        high = (int) (240 * scale);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;

        Bitmap im = decodeResource(context.getResources(), R.drawable.splash, options);
        slides[0] = new slide();
        slides[0].bm = createScaledBitmap(im, wide, high, true);
        slides[0].setGcode(1); // take you to first menu

        im = decodeResource(context.getResources(), R.drawable.help1, options);
        slides[1] = new slide();
        slides[1].bm = createScaledBitmap(im, wide, high, true);
        slides[1].setGcode(5); // take you to help 2

        im = decodeResource(context.getResources(), R.drawable.help2, options);
        slides[2] = new slide();
        slides[2].bm = createScaledBitmap(im, wide, high, true);
        slides[2].setGcode(1); // take you back to main menu 0

        im = decodeResource(context.getResources(), R.drawable.gbye, options);
        slides[3] = new slide();
        slides[3].bm = createScaledBitmap(im, wide, high, true);
        slides[3].setGcode(-1);
    }

    public int hitButton(int slidenum, MotionEvent event) {

        return slides[slidenum].getGcode();
    }

    public void drawSlide(Canvas canvas, int slidenum, float screenWide, float screenHigh) {
        int left = (int) (screenWide - slides[slidenum].bm.getWidth()) / 2;
        int top = (int) (screenHigh - slides[slidenum].bm.getHeight()) / 2;
        canvas.drawBitmap(slides[slidenum].bm, left, top, null);
    }

    private static class slide {
        Bitmap bm;
        int gcode;

        public void setGcode(int gcode) {
            this.gcode = gcode;
        }

        public int getGcode() {
            return gcode;
        }

    }


}
