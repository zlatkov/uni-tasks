package com.zlatkov;

public class Board {
    public final static int SIZE = 3;
    private final CellType[][] cells = new CellType[Board.SIZE][Board.SIZE];

    public Board() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                this.clearCell(new BoardPosition(i, j));
            }
        }
    }

    public void markCell(CellType cell, BoardPosition position) {
        this.cells[position.getRow()][position.getCol()] = cell;
    }

    public void clearCell(BoardPosition position) {
        this.cells[position.getRow()][position.getCol()] = CellType.EMPTY;
    }

    public CellType getCell(BoardPosition position) {
        return this.cells[position.getRow()][position.getCol()];
    }

    public boolean isEmptyCell(BoardPosition position) {
        return this.cells[position.getRow()][position.getCol()] == CellType.EMPTY;
    }
}
