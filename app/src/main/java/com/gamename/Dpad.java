package com.gamename;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Dpad {
    public dpadbutts[] dpadbutt = new dpadbutts[13];
    final private Paint paint = new Paint();
    public Dpad(Rect vport) {

        int bsize = vport.left / 5;
        /* top row */
        dpadbutt[0] = new dpadbutts();
        dpadbutt[0].bounds = new Rect();
        dpadbutt[0].setRection(1);
        dpadbutt[0].setBounds(vport.left - (bsize * 4), vport.top + bsize, vport.left - (bsize * 3), vport.top + (bsize * 2));

        dpadbutt[1] = new dpadbutts();
        dpadbutt[1].bounds = new Rect();
        dpadbutt[1].setRection(2);
        dpadbutt[1].setBounds(dpadbutt[0].getRight(), dpadbutt[0].getTop(), dpadbutt[0].getRight() + bsize, dpadbutt[0].getBottom());

        dpadbutt[2] = new dpadbutts();
        dpadbutt[2].bounds = new Rect();
        dpadbutt[2].setRection(3);
        dpadbutt[2].setBounds(dpadbutt[1].getRight(), dpadbutt[0].getTop(), dpadbutt[1].getRight() + bsize, dpadbutt[0].getBottom());

        /* middle row */
        dpadbutt[3] = new dpadbutts();
        dpadbutt[3].bounds = new Rect();
        dpadbutt[3].setRection(4);
        dpadbutt[3].setBounds(dpadbutt[0].getLeft(), dpadbutt[0].getBottom(), dpadbutt[0].getRight(), dpadbutt[0].getBottom() + bsize);

        dpadbutt[4] = new dpadbutts();
        dpadbutt[4].bounds = new Rect();
        dpadbutt[4].setRection(5);
        dpadbutt[4].setBounds(dpadbutt[1].getLeft(), dpadbutt[0].getBottom(), dpadbutt[1].getRight(), dpadbutt[3].getBottom());

        dpadbutt[5] = new dpadbutts();
        dpadbutt[5].bounds = new Rect();
        dpadbutt[5].setRection(6);
        dpadbutt[5].setBounds(dpadbutt[2].getLeft(), dpadbutt[0].getBottom(), dpadbutt[2].getRight(), dpadbutt[3].getBottom());

        /* bottom row */
        dpadbutt[6] = new dpadbutts();
        dpadbutt[6].bounds = new Rect();
        dpadbutt[6].setRection(7);
        dpadbutt[6].setBounds(dpadbutt[0].getLeft(), dpadbutt[3].getBottom(), dpadbutt[0].getRight(), dpadbutt[3].getBottom() + bsize);

        dpadbutt[7] = new dpadbutts();
        dpadbutt[7].bounds = new Rect();
        dpadbutt[7].setRection(8);
        dpadbutt[7].setBounds(dpadbutt[1].getLeft(), dpadbutt[6].getTop(), dpadbutt[1].getRight(), dpadbutt[6].getBottom());

        dpadbutt[8] = new dpadbutts();
        dpadbutt[8].bounds = new Rect();
        dpadbutt[8].setRection(9);
        dpadbutt[8].setBounds(dpadbutt[2].getLeft(), dpadbutt[6].getTop(), dpadbutt[2].getRight(), dpadbutt[6].getBottom());
    // a b x y
        dpadbutt[9] = new dpadbutts();
        dpadbutt[9].bounds = new Rect();
        dpadbutt[9].setRection(10); // '0xA'
        dpadbutt[9].setBounds(vport.right + (bsize * 2), vport.top + bsize, vport.right + (bsize * 3), vport.top + (bsize * 2));

        dpadbutt[10] = new dpadbutts();
        dpadbutt[10].bounds = new Rect();
        dpadbutt[10].setRection(11); // '0xA'
        dpadbutt[10].setBounds(dpadbutt[9].getLeft() - bsize, dpadbutt[9].getTop() + bsize, dpadbutt[9].getLeft(), dpadbutt[9].getBottom() + bsize);

        dpadbutt[11] = new dpadbutts();
        dpadbutt[11].bounds = new Rect();
        dpadbutt[11].setRection(11);
        dpadbutt[11].setBounds(dpadbutt[9].getRight(), dpadbutt[9].getBottom(), dpadbutt[9].getRight() + bsize, dpadbutt[9].getBottom() + bsize);

        dpadbutt[12] = new dpadbutts();
        dpadbutt[12].bounds = new Rect();
        dpadbutt[12].setRection(12);
        dpadbutt[12].setBounds(dpadbutt[10].getRight(), dpadbutt[10].getBottom(), dpadbutt[11].getLeft(), dpadbutt[10].getBottom() + bsize);
    }

    public int hitButton(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (dpadbutts button : dpadbutt) {
            if (button.bounds.contains(x, y))
                return (button.getRection());
        }
        return 0;
    }

    public void draw(Canvas canvas){
        int width = 4;
        int shadowidth = 8;
        float cx;
        float cy;
        float radius;
        if(canvas != null) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(width);
            canvas.drawRect(dpadbutt[1].getBounds(), paint);
            canvas.drawRect(dpadbutt[3].getBounds(), paint);
            canvas.drawRect(dpadbutt[5].getBounds(), paint);
            canvas.drawRect(dpadbutt[7].getBounds(), paint);
            paint.setColor(Color.BLACK);
            canvas.drawLine(dpadbutt[1].bounds.left, dpadbutt[1].bounds.bottom, dpadbutt[1].bounds.right, dpadbutt[1].bounds.bottom, paint);
            canvas.drawLine(dpadbutt[3].bounds.right, dpadbutt[3].bounds.top, dpadbutt[3].bounds.right, dpadbutt[3].bounds.bottom, paint);
            canvas.drawLine(dpadbutt[5].bounds.left, dpadbutt[5].bounds.top, dpadbutt[5].bounds.left, dpadbutt[5].bounds.bottom, paint);
            canvas.drawLine(dpadbutt[7].bounds.left, dpadbutt[7].bounds.top, dpadbutt[7].bounds.right, dpadbutt[7].bounds.top, paint);
            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(shadowidth);
            canvas.drawLine(dpadbutt[1].bounds.left - shadowidth, dpadbutt[1].bounds.top + width, dpadbutt[1].bounds.left - shadowidth, dpadbutt[1].bounds.bottom - width, paint);
            canvas.drawLine(dpadbutt[3].bounds.left - shadowidth, dpadbutt[3].bounds.top, dpadbutt[3].bounds.left - shadowidth, dpadbutt[3].bounds.bottom + shadowidth, paint);
            canvas.drawLine(dpadbutt[3].bounds.left - shadowidth, dpadbutt[3].bounds.bottom + shadowidth, dpadbutt[3].bounds.right - shadowidth, dpadbutt[3].bounds.bottom + shadowidth, paint);
            canvas.drawLine(dpadbutt[5].bounds.left + width, dpadbutt[5].bounds.bottom + shadowidth, dpadbutt[5].bounds.right - width, dpadbutt[5].bounds.bottom + shadowidth, paint);
            canvas.drawLine(dpadbutt[7].bounds.left - shadowidth, dpadbutt[7].bounds.top + width, dpadbutt[7].bounds.left - shadowidth, dpadbutt[7].bounds.bottom + shadowidth, paint);
            canvas.drawLine(dpadbutt[7].bounds.left - shadowidth, dpadbutt[7].bounds.bottom + shadowidth, dpadbutt[7].bounds.right - width, dpadbutt[7].bounds.bottom + shadowidth, paint);

            paint.setColor(Color.BLUE);
            // canvas.drawRect(dpadbutt[9].getBounds(), paint);
            cx = dpadbutt[9].getBounds().exactCenterX();
            cy = dpadbutt[9].getBounds().exactCenterY();
            radius = (float)dpadbutt[9].getBounds().width() / 2;
            canvas.drawCircle(cx, cy, radius, paint);

            paint.setColor(Color.GREEN);
            // canvas.drawRect(dpadbutt[10].getBounds(), paint);
            cx = dpadbutt[10].getBounds().exactCenterX();
            cy = dpadbutt[10].getBounds().exactCenterY();
            radius = (float)dpadbutt[10].getBounds().width() / 2;
            canvas.drawCircle(cx, cy, radius, paint);

            paint.setColor(Color.RED);
            // canvas.drawRect(dpadbutt[11].getBounds(), paint);
            cx = dpadbutt[11].getBounds().exactCenterX();
            cy = dpadbutt[11].getBounds().exactCenterY();
            radius = (float)dpadbutt[11].getBounds().width() / 2;
            canvas.drawCircle(cx, cy, radius, paint);

            paint.setColor(Color.YELLOW);
            // canvas.drawRect(dpadbutt[12].getBounds(), paint);
            cx = dpadbutt[12].getBounds().exactCenterX();
            cy = dpadbutt[12].getBounds().exactCenterY();
            radius = (float)dpadbutt[12].getBounds().width() / 2;
            canvas.drawCircle(cx, cy, radius, paint);
        }
    }

    private static class dpadbutts {
        Rect bounds;
        int rection;

        public void setBounds(int left, int top, int right, int bottom) {
            bounds.left = left;
            bounds.top = top;
            bounds.right = right;
            bounds.bottom = bottom;
        }

        public int getLeft() {
            return bounds.left;
        }
        public int getTop() {
            return bounds.top;
        }
        public int getRight() {
            return bounds.right;
        }
        public int getBottom() {
            return bounds.bottom;
        }
        public Rect getBounds() {
            return bounds;
        }
        public void setRection(int direction) {
            rection = direction;
        }
        public int getRection() {
            return rection;
        }

    }
}
