package com.gamename;

import android.graphics.Point;
import android.graphics.Rect;

public class Player {
    int sprite;
    int speed;
    int direction;
    int score;
    Point spot;
    Rect[] hotz = new Rect[3];
    int[] zsizes;
    /**
     * x is vertical y is horizontal center of player sprite
     */
    public Player (PlayingField pf) {
        sprite = 0;
        speed = 0;
        direction = 0;
        score = 0;
        final int x = (pf.getVport().width() / 2) + pf.getVportLeft();
        final int y = (pf.getVport().height() / 2) + pf.getVportTop();
        spot = new Point(x, y);
        zsizes = new int[] { 36 * pf.getScaleFactor(), 24 * pf.getScaleFactor(), 16 * pf.getScaleFactor() };
        for (int i = 0; i < 3; i++) {
            hotz[i] = new Rect();
        }
    }

    public Rect[] getHotz() { // hot zones surround player one
         // squish
        hotz[0].left = spot.x - zsizes[0];
        hotz[0].top = spot.y - zsizes[0];
        hotz[0].right = spot.x + zsizes[0];
        hotz[0].bottom = spot.y + zsizes[0];
        // squished
        hotz[1].left = spot.x - zsizes[1];
        hotz[1].top = spot.y - zsizes[1];
        hotz[1].right = spot.x + zsizes[1];
        hotz[1].bottom = spot.y + zsizes[1];

        hotz[2].left = spot.x - zsizes[2];
        hotz[2].top = spot.y - zsizes[2];
        hotz[2].right = spot.x + zsizes[2];
        hotz[2].bottom = spot.y + zsizes[2];
        return hotz;
    }

    public void adjustPlayer(PlayingField pf) {
        int x = spot.x;
        int y = spot.y;
        if (speed > 0) {
            switch (direction){
                case 1:
                    if ((x - speed) > pf.getVportLeft())
                        spot.x = x - speed;
                    if ((y - speed) > pf.getVportTop())
                        spot.y = y - speed;
                    break;
                case 2:
                    if ((y - speed) > pf.getVportTop())
                        spot.y = y - speed;
                    break;
                case 3:
                    if ((y - speed) > pf.getVportTop())
                        spot.y = y - speed;
                    if ((x + speed) < pf.getVportRight())
                        spot.x = x + speed;
                    break;
                case 4:
                    if ((x - speed) > pf.getVportLeft())
                        spot.x = x - speed;
                    break;
                case 5:
                    break;
                case 6:
                    if ((x + speed) < pf.getVportRight())
                        spot.x = x + speed;
                    break;
                case 7:
                    if ((x - speed) > pf.getVportLeft())
                        spot.x = x - speed;
                    if ((y + speed) < pf.getVportBottom())
                        spot.y = y + speed;
                    break;
                case 8:
                    if ((y + speed) < pf.getVportBottom())
                        spot.y = y + speed;
                    break;
                case 9:
                    if ((y + speed) < pf.getVportBottom())
                        spot.y = y + speed;
                    if ((x + speed) < pf.getVportRight())
                        spot.x = x + speed;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        if (this.speed < 0) {
            speed = 0;
        }
        this.speed = speed;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
