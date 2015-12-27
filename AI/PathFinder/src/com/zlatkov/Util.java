package com.zlatkov;

public class Util {
    public static int[][] copy(int[][] data) {
        int[][] result = new int[data.length][];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i].clone();
        }

        return result;
    }
}
