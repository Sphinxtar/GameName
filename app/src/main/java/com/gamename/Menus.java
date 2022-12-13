package com.gamename;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Menus {
    final private Paint paint = new Paint();
    final private mybutt[] buttons = new mybutt[4];
    final private int textSize;

    public Menus(int ScreenHigh, int ScreenWide){
        textSize = (ScreenHigh / 12);
        paint.setTextSize(textSize);

        buttons[0] = new mybutt();
        buttons[0].label = "Start The Game";
        buttons[0].bounds = new Rect();
        buttons[0].bounds.top = (ScreenHigh / 16) * 2;
        buttons[0].bounds.left = ScreenWide / 4;
        buttons[0].bounds.bottom = buttons[0].bounds.top + (ScreenHigh / 16) * 2;
        buttons[0].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0].setGcode(0);

        buttons[1] = new mybutt();
        buttons[1].label = "High Scores";
        buttons[1].bounds = new Rect();
        buttons[1].bounds.top = (ScreenHigh / 16) * 5;
        buttons[1].bounds.left = ScreenWide / 4;
        buttons[1].bounds.bottom = buttons[1].bounds.top + (ScreenHigh / 16) * 2;
        buttons[1].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[1].setGcode(6);

        buttons[2] = new mybutt();
        buttons[2].label = "How To Play";
        buttons[2].bounds = new Rect();
        buttons[2].bounds.top = (ScreenHigh / 16) * 8;
        buttons[2].bounds.left = ScreenWide / 4;
        buttons[2].bounds.bottom = buttons[2].bounds.top + (ScreenHigh / 16) * 2;
        buttons[2].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[2].setGcode(4);

        buttons[3] = new mybutt();
        buttons[3].label = "Quit";
        buttons[3].bounds = new Rect();
        buttons[3].bounds.top = (ScreenHigh / 16) * 11;
        buttons[3].bounds.left = ScreenWide / 4;
        buttons[3].bounds.bottom = buttons[3].bounds.top + (ScreenHigh / 16) * 2;
        buttons[3].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[3].setGcode(7);
    }

    public int hitButton(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].bounds.contains(x, y)) {
                return (buttons[i].getGcode());
            }
        }
        return(1);
    }

    public void draw( Canvas canvas ){
        if(canvas != null) {
            canvas.drawRGB(0, 100, 105);
            for (int i = 0; i < buttons.length; i++) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.LTGRAY);
                canvas.drawRect(buttons[i].bounds, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(16);
                canvas.drawLine(buttons[i].bounds.left,
                buttons[i].bounds.bottom,
                buttons[i].bounds.right,
                buttons[i].bounds.bottom,
                paint);
                canvas.drawLine(buttons[i].bounds.right,
                        buttons[i].bounds.top,
                        buttons[i].bounds.right,
                        buttons[i].bounds.bottom,
                        paint);
                paint.setStrokeWidth(4);
                canvas.drawRect(buttons[i].bounds, paint);

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                drawCenter(canvas, paint, buttons[i].bounds, buttons[i].label);
            }
        }
    }

    private void drawCenter(Canvas canvas, Paint paint, Rect r, String text) {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        float x = r.left + (float) ((r.right - r.left) / 2);
        float y = r.top + (float) ((r.bottom - r.top) / 2);
        canvas.drawText(text, x, y + (float) textSize / 3, paint);
    }

    private static class mybutt {
        Rect bounds;
        String label;
        int gcode;

        public void setGcode(int gcode) {
            this.gcode = gcode;
        }

        public int getGcode() {
            return this.gcode;
        }
    }
}
