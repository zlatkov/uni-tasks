package com.zlatkov;

import java.util.*;

public class Board {
    private static int[] rowOccurrences;
    private static int[] firstDiagonalOccurrences;
    private static int[] secondDiagonalOccurrences;
    private final int[] queenRows;

    public Board(int size) {
        this.queenRows = new int[size];

        for (int i = 0; i < size; i++) {
            this.queenRows[i] = Util.getRandom(size);
        }
    }

    public int getSize() {
        return this.queenRows.length;
    }

    public int getQueenRow(int column) {
        return this.queenRows[column];
    }

    public void moveQueenAtColumn(int column) {
        int currentRow = this.queenRows[column];
        int minConflictsCount = Integer.MAX_VALUE;
        List<Integer> minConflictsRows = new ArrayList<>();

        for (int i = 0; i < this.queenRows.length; i++) {
            if (i != currentRow) {
                int conflictsCount = this.getConflictsCount(i, column);
                if (conflictsCount < minConflictsCount) {
                    minConflictsCount = conflictsCount;
                    minConflictsRows.clear();
                    minConflictsRows.add(i);
                }
                else if (conflictsCount == minConflictsCount) {
                    minConflictsRows.add(i);
                }
            }
        }

        this.queenRows[column] = minConflictsRows.get(Util.getRandom((minConflictsRows.size())));
    }

    public List<Integer> getConflictColumns() {
        List<Integer> conflicts = new ArrayList<>();

        int queensCount = this.queenRows.length;
        rowOccurrences = new int[queensCount];
        firstDiagonalOccurrences = new int[2 * queensCount - 1];
        secondDiagonalOccurrences = new int[2 * queensCount - 1];

        for (int i = 0; i < queensCount; i++) {
            int queenRow = this.queenRows[i];
            rowOccurrences[queenRow]++;

            int firstDiagonalIndex = getFirstDiagonalIndex(queenRow, i);
            int secondDiagonalIndex = getSecondDiagonalIndex(queenRow, i);
            firstDiagonalOccurrences[firstDiagonalIndex]++;
            secondDiagonalOccurrences[secondDiagonalIndex]++;
        }

        for (int i = 0; i < queensCount; i++) {
            int queenRow = this.queenRows[i];
            int queenConflictsCount = this.getConflictsCount(queenRow, i);

            if (queenConflictsCount > 0) {
                conflicts.add(i);
            }
        }

        return conflicts;
    }

    private int getFirstDiagonalIndex(int row, int col) {
        return row + col;
    }

    private int getSecondDiagonalIndex(int row, int col) {
        return this.queenRows.length - 1 + row - col;
    }

    private int getConflictsCount(int row, int col) {
        int firstDiagonalIndex = getFirstDiagonalIndex(row, col);
        int secondDiagonalIndex = getSecondDiagonalIndex(row, col);

        return rowOccurrences[row] +
            firstDiagonalOccurrences[firstDiagonalIndex] +
            secondDiagonalOccurrences[secondDiagonalIndex] - 3;
    }
}
