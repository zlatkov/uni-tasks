package com.zlatkov;

import java.util.*;

public class Board {
    private int[] rowOccurrences;
    private int[] firstDiagonalOccurrences;
    private int[] secondDiagonalOccurrences;
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

    public List<Integer> getConflictingColumns() {
        List<Integer> conflicts = new ArrayList<>();

        int queensCount = this.queenRows.length;
        this.rowOccurrences = new int[queensCount];
        this.firstDiagonalOccurrences = new int[2 * queensCount - 1];
        this.secondDiagonalOccurrences = new int[2 * queensCount - 1];

        for (int i = 0; i < queensCount; i++) {
            int queenRow = this.queenRows[i];
            this.rowOccurrences[queenRow]++;

            int firstDiagonalIndex = getFirstDiagonalIndex(queenRow, i);
            int secondDiagonalIndex = getSecondDiagonalIndex(queenRow, i);
            this.firstDiagonalOccurrences[firstDiagonalIndex]++;
            this.secondDiagonalOccurrences[secondDiagonalIndex]++;
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

        return this.rowOccurrences[row] +
                this.firstDiagonalOccurrences[firstDiagonalIndex] +
                this.secondDiagonalOccurrences[secondDiagonalIndex] - 3;
    }
}
