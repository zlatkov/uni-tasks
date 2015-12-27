package com.zlatkov;

public class BoardState {
    private int[][] stateValues = new int[Board.SIZE][Board.SIZE];
    private BoardPosition emptyCellPosition;

    public BoardState(int[][] stateValues) {
        this.stateValues = stateValues.clone();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (this.stateValues[i][j] == Board.EMPTY_CELL) {
                    this.emptyCellPosition = new BoardPosition(i, j);
                }
            }
        }
    }

    public int getValue(BoardPosition position) {
        return this.getValue(position.getRow(), position.getCol());
    }

    public int getValue(int row, int col) {
        return this.stateValues[row][col];
    }

    public BoardPosition getEmptyCellPosition() { return this.emptyCellPosition; }

    public BoardState move(BoardPosition deltaPosition) {
        int emptyCellRow = this.getEmptyCellPosition().getRow();
        int emptyCellCol = this.getEmptyCellPosition().getCol();
        BoardPosition nextPosition = new BoardPosition(emptyCellRow + deltaPosition.getRow(), emptyCellCol + deltaPosition.getCol());

        if (nextPosition.getRow() >= 0 && nextPosition.getRow() < 3
                && nextPosition.getCol() >= 0 && nextPosition.getCol() < 3) {
            int[][] nextStateValues = Util.copy(this.stateValues);
            nextStateValues[nextPosition.getRow()][nextPosition.getCol()] = this.stateValues[emptyCellRow][emptyCellCol];
            nextStateValues[emptyCellRow][emptyCellCol] = this.stateValues[nextPosition.getRow()][nextPosition.getCol()];

            return new BoardState(nextStateValues);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BoardState)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        BoardState otherState = (BoardState)obj;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (this.getValue(i, j) != otherState.getValue(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                result = result * 10 + this.getValue(i, j);
            }
        }

        return result;
    }
}
