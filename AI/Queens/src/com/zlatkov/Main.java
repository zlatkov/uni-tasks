package com.zlatkov;

import java.util.*;

public class Main {
    private static final int MAX_ITERATIONS = 10000000;

    private static void printBoard(Board board) {
        int boardSize = board.getSize();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                System.out.print(board.getQueenRow(col) == row ? "Q " : "* ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Board board = new Board(n);
        int iterations = 0;
        List<Conflict> conflicts = board.getConflicts();

        while (conflicts.size() > 0 && iterations < MAX_ITERATIONS) {
            Conflict conflict = conflicts.get(Util.getRandom(conflicts.size()));
            board.moveQueenAtColumn(conflict.getColumn());
            conflicts = board.getConflicts();
            iterations++;
        }

        if (conflicts.size() > 0) {
            System.out.print("Did not manage to find a solution.");
        }
        else {
            printBoard(board);
        }
    }
}
