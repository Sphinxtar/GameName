package com.gamename;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// type:state:x,y:direction:tts:respawn:birth:life:death:birthsound:lifesound,interval:deathrattle:
// 0:1:64,65::6:20:4:4:9:0:0,20:0"
public class Npc {

    public final Bot[] bots = new Bot[4];
    public final Zone[] zones = new Zone[17];

    public Npc(Context context, PlayingField pf) {
        loadBots(context, pf);
    }

    private void loadBots(Context context, PlayingField pf) {
        String line;
        String[] words;
        String[] woids;
        int botnum = 0;
        int zonenum = 0;
        InputStream in;
        try {
            in = context.getAssets().open("npc");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while (true) {
            try {
                if ((line = reader.readLine()) == null) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.isEmpty()) {
                continue;
            }
            // n:type:state:x,y  :rection:tts:respawn:sprite:birthsound:lifesound,interval:deathrattle:
            words = line.split(":");
            if (words[0]. equals("n")) {
                bots[botnum] = new Bot();
                bots[botnum].spot = new Point();
                bots[botnum].setType(Integer.parseInt(words[1]));
                bots[botnum].setState(Integer.parseInt(words[2]));
                woids = words[3].split(",");
                bots[botnum].setSpot((pf.getVportLeft() + (Integer.parseInt(woids[0]) * pf.scalefactor)),
                        (pf.getVportTop() + (Integer.parseInt(woids[1]) * pf.scalefactor)));
                bots[botnum].setRection(Integer.parseInt(words[4]));
                if (bots[botnum].getRection() == 0) {
                    if (Dragon.getRandom(1, 49) % 2 == 0)
                        bots[botnum].setRection(Dragon.getRandom(1, 4));
                    else
                        bots[botnum].setRection(Dragon.getRandom(6, 9));
                }
                bots[botnum].setTts(Integer.parseInt(words[5]));
                bots[botnum].setRespawn(Integer.parseInt(words[6]));
                bots[botnum].setSprite(Integer.parseInt(words[7]));
                bots[botnum].setBirthsound(Integer.parseInt(words[8]));
                woids = words[9].split(",");
                bots[botnum].setLifesound(Integer.parseInt(woids[0]));
                bots[botnum].setInterval(Integer.parseInt(woids[1]));
                bots[botnum].setDeathrattle(Integer.parseInt(words[10]));
                botnum++;
            }
            if (words[0]. equals("z")) {
                zones[zonenum] = new Zone();
                zones[zonenum].area = new Rect();
                zones[zonenum].area.left = (pf.getVportLeft() + (Integer.parseInt(words[1]) * pf.scalefactor));
                zones[zonenum].area.top = (pf.getVportTop() + (Integer.parseInt(words[2]) * pf.scalefactor));
                zones[zonenum].area.right = (pf.getVportLeft() + (Integer.parseInt(words[3]) * pf.scalefactor));
                zones[zonenum].area.bottom = (pf.getVportTop() + (Integer.parseInt(words[4]) * pf.scalefactor));
                zones[zonenum].sprite[0] = (Integer.parseInt(words[5]));
                zones[zonenum].sprite[1] = (Integer.parseInt(words[6]));
                zonenum++;
            }
        }
        try {
            in.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void collisions() {
        for(Zone z : zones) {
            for(Bot b : bots) {
                if (z.area.contains(b.spot.x, b.spot.y)) {
                    b.sprite = z.sprite[b.state];
                }
           }
        }
    }

    private static class Zone {
        private Rect area;
        private final int[] sprite;
        {
            sprite = new int[2];
        }

    }

    public static class Bot {
        int type;
        int state;
        Point spot; // virtual world coordinates
        int speed; // amount to increment
        int rection; // direct on keypad to increment x and y 0 or 5 is stopped
        int tts; // time til spin - at 0 reset to a random amount and changes direction/speed
        int respawn;
        int sprite;
        int birthsound;
        int lifesound;
        int interval;
        int deathrattle;

        public int getRespawn() { return respawn; }
        public void setRespawn(int respawn) { this.respawn = respawn; }
        public int getSpeed() { return speed; }
        public void setSpeed(int speed) { this.speed = speed; }
        public int getTts() { return tts; }
        public void setTts(int tts) { this.tts = tts; }
        public int getBirthsound() { return birthsound; }
        public void setBirthsound(int birthsound) { this.birthsound = birthsound; }
        public int getLifesound() { return lifesound; }
        public void setLifesound(int lifesound) { this.lifesound = lifesound; }
        public int getInterval() { return interval; }
        public void setInterval(int interval) { this.interval = interval; }
        public int getDeathrattle() { return deathrattle; }
        public void setDeathrattle(int deathrattle) { this.deathrattle = deathrattle; }
        public int getSprite() { return sprite; }
        public void setSprite(int sprite) { this.sprite = sprite; }
        public int getRection() { return rection; }
        public void setRection(int rection) { this.rection = rection; }
        public void setType(int type) { this.type = type; }
        public int getType() { return this.type; }
        public void setState(int state) { this.state = state; }
        public int getState() { return this.state; }
        public void setSpot(int x, int y){
            this.spot.x = x; this.spot.y = y;
        }
        public Point getSpot() { return this.spot; }
    }

}
