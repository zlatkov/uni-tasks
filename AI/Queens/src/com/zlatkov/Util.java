package com.zlatkov;

import java.util.Random;

public class Util {
    private static final Random random = new Random();

    public static int getRandom(int n) {
        return random.nextInt(n);
    }
}
