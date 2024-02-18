package com.gamename;

import java.util.Random;

public class Dragon {

    static Random random = new Random();

    public static int getRandom(int r) {
        return random.nextInt(r);
    }
}
