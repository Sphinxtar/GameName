package com.gamename;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Slides {

    public int wide;
    public int high;
    public Bitmap[] slides = new Bitmap[5];
    public Menus menu;

    public Slides(Context context, float scale) {
        wide = (int) (320 * scale);
        high = (int) (240 * scale);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        Bitmap im = decodeResource(context.getResources(), R.drawable.splash, options);
        slides[0] = createScaledBitmap(im, wide, high, true);
        im = decodeResource(context.getResources(), R.drawable.help1, options);
        slides[1] = createScaledBitmap(im, wide, high, true);
        im = decodeResource(context.getResources(), R.drawable.help2, options);
        slides[2] = createScaledBitmap(im, wide, high, true);
        im = decodeResource(context.getResources(), R.drawable.gbye, options);
        slides[3] = createScaledBitmap(im, wide, high, true);
    }

    public void drawSlide(Canvas canvas, int slide, int screenWide ) {
        int offset = screenWide - wide / 2;
        canvas.drawBitmap(slides[slide], offset, 0, null);
    }

}
