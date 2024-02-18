package com.gamename;

// type:state:x,y:direction:tts:respawn:birth:life:death:birthsound:lifesound,interval:deathrattle:
public class Npc {
    short type;
    short state;
    short x;
    short y; // virtual world coordinates
    short speed; // amount to increment
    short rection; // direct on keypad to increment x and y 0 or 5 is stopped
    short tts; // time til spin - at 0 reset to a random amount and changes direction/speed
    short[] birth; //indexes into big sprite bitmap array or null
    short[] life; // indexes into sprites bitmap array or null
    short[] death; // indexes into sprites bitmap array or null
    short bitrthsound; // index into racket array
    short lifesound; // index into racket array
    short interval;
    short deathrattle; // index into racket array

    public Npc() {
    }

    public void setX( short X) {
        this.x = X;
    }

    public short getX() {
        return this.x;
    }

    public void setY( short Y) {
        this.y = Y;
    }

    public short getY() {
        return this.y;
    }

    public void setSpeed(short Speed) {
        this.speed = Speed;
    }

    public short getSpeed() {
        return this.speed;
    }

    public void setRection(short Rection) {
        this.rection = Rection;
    }

    public short getRection() {
        return rection;
    }

    public void setTts(short tts) { this.tts = tts; }

    public short getTts() {
        return tts;
    }
}
