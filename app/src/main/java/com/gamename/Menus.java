package com.gamename;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Menus {
    final private Paint paint = new Paint();
    final private mybutt[][] buttons = new mybutt[2][3];
    final private int textSize;

    public Menus(int ScreenHigh, int ScreenWide){
        textSize = (ScreenHigh / 12);
        paint.setTextSize(textSize);

        // She watch Menu Zero - current menu/row == gcode
        buttons[0][0] = new mybutt();
        buttons[0][0].label = "Start";
        buttons[0][0].bounds = new Rect();
        buttons[0][0].bounds.top = (ScreenHigh / 12) * 2;
        buttons[0][0].bounds.left = ScreenWide / 4;
        buttons[0][0].bounds.bottom = buttons[0][0].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][0].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][0].setGcode(0);

        buttons[0][1] = new mybutt();
        buttons[0][1].label = "Help";
        buttons[0][1].bounds = new Rect();
        buttons[0][1].bounds.top = (ScreenHigh / 12) * 5;
        buttons[0][1].bounds.left = ScreenWide / 4;
        buttons[0][1].bounds.bottom = buttons[0][1].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][1].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][1].setGcode(4);

        buttons[0][2] = new mybutt();
        buttons[0][2].label = "Quit";
        buttons[0][2].bounds = new Rect();
        buttons[0][2].bounds.top = (ScreenHigh / 12) * 8;
        buttons[0][2].bounds.left = ScreenWide / 4;
        buttons[0][2].bounds.bottom = buttons[0][2].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][2].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][2].setGcode(6);
    }

    public int hitButton(int menu, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (int i = 0; i < buttons[menu].length; i++) {
            if (buttons[menu][i].bounds.contains(x, y)) {
               return(buttons[menu][i].getGcode());
            }
        }
        return(menu+1);
    }

    public void draw( Canvas canvas, int gstate ){
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
        float x = r.left + (float) ((r.right - r.left) / 2);
        float y = r.top + (float) ((r.bottom - r.top) / 2);
        canvas.drawText(text, x, y + (float) textSize / 2, paint);
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
