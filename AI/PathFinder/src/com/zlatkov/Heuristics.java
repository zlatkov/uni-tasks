package com.zlatkov;

public class Heuristics {
    public static int calculateManhattanDistance(BoardState state) {
        int manhattanDistance = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                int value = state.getValue(i, j);
                if (value != Board.EMPTY_CELL) {
                    int targetRow = (value - 1) / Board.SIZE;
                    int targetCol = (value - 1) % Board.SIZE;
                    int deltaRow = i - targetRow;
                    int deltaCol = j - targetCol;
                    manhattanDistance += Math.abs(deltaRow) + Math.abs(deltaCol);
                }
            }
        }

        return manhattanDistance;
    }
}
