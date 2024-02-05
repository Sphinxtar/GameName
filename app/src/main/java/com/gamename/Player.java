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
        final int x = (pf.getVport().width() / 2) + pf.getVportLeft();
        final int y = (pf.getVport().height() / 2) + pf.getVportTop();
        spot = new Spot(x,y);
    }

    public void adjustPlayer(PlayingField pf) {
        int x = spot.getX();
        int y = spot.getY();
        if (speed > 0) {
            switch (direction){
                case 1:
                    if ((x - speed) > pf.getVportLeft())
                        spot.setX(x - speed);
                    if ((y - speed) > pf.getVportTop())
                        spot.setY(y - speed);
                    break;
                case 2:
                    if ((y - speed) > pf.getVportTop())
                        spot.setY(y - speed);
                    break;
                case 3:
                    if ((y - speed) > pf.getVportTop())
                        spot.setY(y - speed);
                    if ((x + speed) < pf.getVportRight())
                        spot.setX(x + speed);
                    break;
                case 4:
                    if ((x - speed) > pf.getVportLeft())
                        spot.setX(x - speed);
                    break;
                case 5:
                    break;
                case 6:
                    if ((x + speed) < pf.getVportRight())
                        spot.setX(x + speed);
                    break;
                case 7:
                    if ((x - speed) > pf.getVportLeft())
                        spot.setX(x - speed);
                    if ((y + speed) < pf.getVportBottom())
                        spot.setY(y + speed);
                    break;
                case 8:
                    if ((y + speed) < pf.getVportBottom())
                        spot.setY(y + speed);
                    break;
                case 9:
                    if ((y + speed) < pf.getVportBottom())
                        spot.setY(y + speed);
                    if ((x + speed) < pf.getVportRight())
                        spot.setX(x + speed);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
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
        if (this.speed < 0)
            speed = 0;
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
