package com.gamename;
/*
  load a bitmap and cut it into an array of smaller bitmaps according to a text file in assets
  draw them where specified by index and center coordinates
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;
import androidx.annotation.NonNull;

public class Sprite {
    private final Bitmap[] sprites = new Bitmap[23]; // array of sprites cut and scaled

    public Sprite( Context context, PlayingField pf ) {
        loadSprites(context, pf.scalefactor);
    }

    private void loadSprites(Context context, int scaling) {
        BufferedReader reader;
        try {
            int x;
            int y;
            int wide;
            int high;
            Bitmap im = null; // intermediate bitmap for scaling
            Bitmap SpriteSheet = decodeResource(context.getResources(), R.drawable.sprites, null);
            String line;
            String[] words;
            InputStream in = context.getAssets().open("sprites");
            reader = new BufferedReader(new InputStreamReader(in));
            int spritenum = 0;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                words = line.split(",");
                if (words[0].equals("q")) {
                    x = (Short.parseShort(words[1]));
                    y = (Short.parseShort(words[2]));
                    wide = (Short.parseShort(words[3]));
                    high = (Short.parseShort(words[4]));
                    im = Bitmap.createBitmap(SpriteSheet, x, y, wide, high );
                    sprites[spritenum++] = createScaledBitmap(im, wide * scaling, high * scaling, true);
                }
            }
            in.close();
            reader.close();
            SpriteSheet.recycle();
            if ( im != null )
                im.recycle();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
/*
    public void drawSprite(@NonNull Canvas canvas, int sprite, int column, int row ) {
        canvas.drawBitmap(sprites[sprite], row, column, null);
    }
*/
    public void drawCenterSprite(@NonNull Canvas canvas, int sprite, int column, int row ) {
        int top = (row - (sprites[sprite].getHeight() / 2));
        int left = (column - (sprites[sprite].getWidth() / 2));
        canvas.drawBitmap(sprites[sprite], left, top, null);
    }

}