package com.gamename;

public class Player {

    int sprite;
    int speed;
    int direction;
    int score;
    Spot spot;

    /**
     * x is vertical y is horizontal center of player sprite
     */
    public Player (PlayingField pf) {
        sprite = 0;
        speed = 0;
        direction = 0;
        score = 0;
        spot = new Spot((pf.getVportRight() - pf.getVportLeft()) / 2,
                (pf.getVportBottom() - pf.getVportTop()) / 2);
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
