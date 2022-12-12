package com.gamename;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Menus {
    final private Paint paint = new Paint();
    final private mybutt[][] buttons = new mybutt[2][4];
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
        buttons[0][1].setGcode(2); // menu 2

        buttons[0][2] = new mybutt();
        buttons[0][2].label = "Quit";
        buttons[0][2].bounds = new Rect();
        buttons[0][2].bounds.top = (ScreenHigh / 12) * 8;
        buttons[0][2].bounds.left = ScreenWide / 4;
        buttons[0][2].bounds.bottom = buttons[0][2].bounds.top + (ScreenHigh / 12) * 2;
        buttons[0][2].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[0][2].setGcode(6);

        buttons[1][0] = new mybutt();
        buttons[1][0].label = "Back";
        buttons[1][0].bounds = new Rect();
        buttons[1][0].bounds.top = (ScreenHigh / 16) * 2;
        buttons[1][0].bounds.left = ScreenWide / 4;
        buttons[1][0].bounds.bottom = buttons[1][0].bounds.top + (ScreenHigh / 16) * 2;
        buttons[1][0].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[1][0].setGcode(1);

        buttons[1][1] = new mybutt();
        buttons[1][1].label = "RTFM";
        buttons[1][1].bounds = new Rect();
        buttons[1][1].bounds.top = (ScreenHigh / 16) * 5;
        buttons[1][1].bounds.left = ScreenWide / 4;
        buttons[1][1].bounds.bottom = buttons[1][1].bounds.top + (ScreenHigh / 16) * 2;
        buttons[1][1].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[1][1].setGcode(2);

        buttons[1][2] = new mybutt();
        buttons[1][2].label = "Top Ten";
        buttons[1][2].bounds = new Rect();
        buttons[1][2].bounds.top = (ScreenHigh / 16) * 8;
        buttons[1][2].bounds.left = ScreenWide / 4;
        buttons[1][2].bounds.bottom = buttons[1][2].bounds.top + (ScreenHigh / 16) * 2;
        buttons[1][2].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[1][2].setGcode(4);

        buttons[1][3] = new mybutt();
        buttons[1][3].label = "Quit";
        buttons[1][3].bounds = new Rect();
        buttons[1][3].bounds.top = (ScreenHigh / 16) * 11;
        buttons[1][3].bounds.left = ScreenWide / 4;
        buttons[1][3].bounds.bottom = buttons[1][3].bounds.top + (ScreenHigh / 16) * 2;
        buttons[1][3].bounds.right = ScreenWide - (ScreenWide / 4);
        buttons[1][3].setGcode(6);
    }

    public int hitButton(int menunum, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (int i = 0; i < buttons[menunum].length; i++) {
            if (buttons[menunum][i].bounds.contains(x, y)) {
               return(buttons[menunum][i].getGcode());
            }
        }
        return(menunum);
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
                paint.setStrokeWidth(16);
                canvas.drawLine(buttons[gstate][i].bounds.left,
                buttons[gstate][i].bounds.bottom,
                buttons[gstate][i].bounds.right,
                buttons[gstate][i].bounds.bottom,
                paint);
                canvas.drawLine(buttons[gstate][i].bounds.right,
                        buttons[gstate][i].bounds.top,
                        buttons[gstate][i].bounds.right,
                        buttons[gstate][i].bounds.bottom,
                        paint);
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
