package com.zlatkov;

import java.util.*;

public class Main {

    private static int[][] readInitialStateValues() {
        Scanner input = new Scanner(System.in);
        int[][] initialStateValues = new int[Board.SIZE][Board.SIZE];
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                initialStateValues[i][j] = input.nextInt();
            }
        }

        return initialStateValues;
    }

    private static void outputState(BoardState state) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }

                System.out.print(state.getValue(i, j));
            }

            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        int[][] initialStateValues = readInitialStateValues();
        Set<BoardState> visitedStates = new HashSet<>();

        Queue<BoardState> states = new LinkedList<>();
        states.add(new BoardState(initialStateValues));
        while (!states.isEmpty()) {
            BoardState state = states.remove();
            visitedStates.add(state);

            if (BoardStateManager.isFinal(state)) {
                outputState(state);
                break;
            }
            else {
                List<BoardState> adjacentStates = BoardStateManager.generateAdjacent(state);
                for (BoardState adjacentState : adjacentStates) {
                    if (!visitedStates.contains(adjacentState)) {
                        states.add(adjacentState);
                    }
                }
            }

        }
    }
}
