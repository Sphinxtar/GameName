package com.gamename;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
//import android.util.Log;

public class Slides {

    public final slide[] slides = new slide[5];

    public Slides(Context context, int Wide, int High) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;

        Bitmap im = decodeResource(context.getResources(), R.drawable.splash, options);
        im.setDensity(Bitmap.DENSITY_NONE);
        slides[0] = new slide();
        slides[0].bm = createScaledBitmap(im, Wide, High,true);
        slides[0].bm.setDensity(Bitmap.DENSITY_NONE);
        slides[0].setGcode(1); // take you to first menu

        im = decodeResource(context.getResources(), R.drawable.help1, options);
        slides[1] = new slide();
        slides[1].bm = createScaledBitmap(im, Wide, High, true);
        slides[1].setGcode(5); // take you to help 2

        im = decodeResource(context.getResources(), R.drawable.help2, options);
        slides[2] = new slide();
        slides[2].bm = createScaledBitmap(im, Wide, High, true);
        slides[2].setGcode(1); // take you back to main menu 1

        im = decodeResource(context.getResources(), R.drawable.topten, options);
        slides[3] = new slide();

        slides[3].bm = createScaledBitmap(im, Wide, High, true);
        slides[3].setGcode(1); // take you back to main menu 1

        im = decodeResource(context.getResources(), R.drawable.gbye, options);
        slides[4] = new slide();
        slides[4].bm = createScaledBitmap(im, Wide, High, true);
        slides[4].setGcode(-1);
    }

    public int hitButton(int slidenum) {

        return slides[slidenum].getGcode();
    }

    public void drawSlide(Canvas canvas, int slidenum, int left, int top) {
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
