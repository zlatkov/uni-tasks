package com.zlatkov;

import com.zlatkov.algorithms.AStart;
import com.zlatkov.algorithms.BeamSearch;
import com.zlatkov.algorithms.Bfs;
import com.zlatkov.algorithms.PathFinder;

import java.util.Scanner;

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
        BoardState initialState = new BoardState(initialStateValues);

        PathFinder algorithm = new BeamSearch(initialState, 2);
        BoardState finalState = algorithm.findPath();
        outputState(finalState);

    }
}
