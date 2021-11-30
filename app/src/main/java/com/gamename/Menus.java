package com.gamename;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Menus {
    private Paint paint = new Paint();
    private mybutt[][] buttons = new mybutt[2][3];
    private int textSize;

    public Menus(int ScreenHigh, int ScreenWide){
        textSize = (ScreenHigh / 12);
        paint.setTextSize(textSize);

        // She watch Menu Zero - current menu/row == gcode
        buttons[0][0] = new mybutt();
        buttons[0][0].label = new String("Start");
        buttons[0][0].bounds = new Rect();
        buttons[0][0].bounds.top = (ScreenHigh / 12) * 2;
        buttons[0][0].bounds.left = ScreenWide / 4;
        buttons[0][0].bounds.bottom = buttons[0][0].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][0].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][0].setGcode(0);

        buttons[0][1] = new mybutt();
        buttons[0][1].label = new String("Help");
        buttons[0][1].bounds = new Rect();
        buttons[0][1].bounds.top = (ScreenHigh / 12) * 5;
        buttons[0][1].bounds.left = ScreenWide / 4;
        buttons[0][1].bounds.bottom = buttons[0][1].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][1].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][1].setGcode(3);

        buttons[0][2] = new mybutt();
        buttons[0][2].label = new String("Quit");
        buttons[0][2].bounds = new Rect();
        buttons[0][2].bounds.top = (ScreenHigh / 12) * 8;
        buttons[0][2].bounds.left = ScreenWide / 4;
        buttons[0][2].bounds.bottom = buttons[0][2].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][2].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][2].setGcode(2);
    }

    public int hitButton(int gstate, MotionEvent event) {
        int retval = gstate;
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (int i = 0; i < buttons[gstate].length; i++) {
            if (buttons[gstate][i].bounds.contains(x, y)) {
               retval = buttons[gstate][i].getGcode();
            }
        }
        return(retval);
    }

    public void draw(int gstate, Canvas canvas) {
        if(canvas != null && buttons[gstate][0] != null ) {
            canvas.drawRGB(0, 100, 105);
            for (int i = 0; i < buttons[gstate].length; i++) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.LTGRAY);
                canvas.drawRect(buttons[gstate][i].bounds, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(4);
                canvas.drawRect(buttons[gstate][i].bounds, paint);

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                drawCenter(canvas, paint, buttons[gstate][i].bounds, buttons[gstate][i].label);
            }
        }
    }

    private void drawCenter(Canvas canvas, Paint paint, Rect r, String text) {
        paint.setTextAlign(Paint.Align.CENTER);
        float x = r.left + ((r.right - r.left) / 2);
        float y = r.top + ((r.bottom - r.top) / 2);
        canvas.drawText(text, x, y + textSize / 2, paint);
    }


    private static class mybutt {
        Rect bounds;
        String label;
        int gcode;

        public void setGcode(int gcode) {
            this.gcode = gcode;
        }

        public int getGcode() {
            return gcode;
        }
    }

}
