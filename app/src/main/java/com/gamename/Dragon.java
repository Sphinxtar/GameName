package com.gamename;

import java.util.Random;

public class Dragon {

    private static final Random random;

    static {
        random = new Random();
    }

    public static int getRandom(int low, int high) {
        return random.nextInt(high - low + 1) + low;
    }

}
