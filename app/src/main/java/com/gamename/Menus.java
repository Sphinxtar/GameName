package com.gamename;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Menus {
    final private Paint paint = new Paint();
    final private mybutt[] buttons = new mybutt[4];
    private final int textSize;

    public Menus(int Left, int Top, int Wide, int High){
        textSize = High / 12;
        paint.setTextSize(textSize);
        int space = 2;

        buttons[0] = new mybutt();
        buttons[0].label = "Start The Game";
        buttons[0].bounds = new Rect();
        buttons[0].bounds.left = (Wide / 4) + Left;
        buttons[0].bounds.top = ((High / 16) * space) + Top;
        buttons[0].bounds.right = (Wide - (Wide / 4)) + Left;
        buttons[0].bounds.bottom = (buttons[0].bounds.top + (High / 16) * 2);
        buttons[0].setGcode(0);

        buttons[1] = new mybutt();
        buttons[1].label = "High Scores";
        buttons[1].bounds = new Rect();
        buttons[1].bounds.left = (Wide / 4) + Left;
        space += 3;
        buttons[1].bounds.top = (space * (High / 16)) + Top;
        buttons[1].bounds.right = (Wide - (Wide / 4)) + Left;
        buttons[1].bounds.bottom = (buttons[1].bounds.top + (High / 16) * 2);
        buttons[1].setGcode(6);

        buttons[2] = new mybutt();
        buttons[2].label = "How To Play";
        buttons[2].bounds = new Rect();
        buttons[2].bounds.left = (Wide / 4) + Left;
        space += 3;
        buttons[2].bounds.top = (space * (High / 16)) + Top;
        buttons[2].bounds.right = (Wide - (Wide / 4)) + Left;
        buttons[2].bounds.bottom = (buttons[2].bounds.top + (High / 16) * 2);
        buttons[2].setGcode(4);

        buttons[3] = new mybutt();
        buttons[3].label = "Quit";
        buttons[3].bounds = new Rect();
        buttons[3].bounds.left = (Wide / 4) + Left;
        space += 3;
        buttons[3].bounds.top = (space * (High / 16)) + Top;
        buttons[3].bounds.right = (Wide - (Wide / 4)) + Left;
        buttons[3].bounds.bottom = (buttons[3].bounds.top + (High / 16) * 2);
        buttons[3].setGcode(7);
    }

    public int hitButton(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (mybutt button : buttons) {
            if (button.bounds.contains(x, y)) {
                return (button.getGcode());
            }
        }
        return(1);
    }

    public void draw(Canvas canvas){
        if(canvas != null) {
            canvas.drawRGB(0, 100, 105);
            for (mybutt button : buttons) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.LTGRAY);
                canvas.drawRect(button.bounds, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(12);
                canvas.drawLine(button.bounds.left,
                        button.bounds.bottom,
                        button.bounds.right,
                        button.bounds.bottom,
                        paint);
                canvas.drawLine(button.bounds.right,
                        button.bounds.top,
                        button.bounds.right,
                        button.bounds.bottom,
                        paint);
                paint.setStrokeWidth(4);
                canvas.drawRect(button.bounds, paint);

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                drawCenter(canvas, paint, button.bounds, button.label);
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
        private int gcode;

        public void setGcode(int gcode) {
            this.gcode = gcode;
        }

        public int getGcode() {
            return this.gcode;
        }
    }
}
