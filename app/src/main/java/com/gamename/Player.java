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
}
