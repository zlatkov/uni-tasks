package com.zlatkov;

import java.util.ArrayList;
import java.util.List;

public class BoardStateManager {

    public static List<BoardState> generateAdjacent(BoardState state) {
        List<BoardState> adjacentStates = new ArrayList<>();
        for (BoardPosition deltaPosition : Board.DELTA_POSITIONS) {
            BoardState adjacentState = state.move(deltaPosition);
            if (adjacentState != null) {
                adjacentStates.add(adjacentState);
            }
        }

        return adjacentStates;
    }

    public static boolean isFinal(BoardState state) {
        int lastValue = Integer.MIN_VALUE;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                int stateValue = state.getValue(i, j);
                if (i == Board.SIZE - 1 && j == Board.SIZE - 1) {
                    if (stateValue != Board.EMPTY_CELL) {
                        return false;
                    }
                }
                else if (stateValue <= lastValue) {
                    return false;
                }

                lastValue = stateValue;
            }
        }

        return true;
    }
}
