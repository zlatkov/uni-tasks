package com.zlatkov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Engine {
    public final Board board = new Board();
    private final Player player1;
    private final Player player2;

    public Engine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void makeTurn(Player player, BoardPosition position) {
        board.markCell(player.getCellType(), position);
    }

    public GameMove getBestMove(Player player) {
        return this.miniMax(player, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isGameOver() {
        for (int i = 0; i < Board.SIZE; i++) {
            if (this.equalCellsByOffset(i, 0, 0, 1)) {
                return true;
            }
            if (this.equalCellsByOffset(0, i, 1, 0)) {
                return true;
            }
        }

        return this.equalCellsByOffset(0, 0, 1, 1) // First diagonal
            || this.equalCellsByOffset(Board.SIZE - 1, 0, -1, 1); // Second diagonal
    }

    public boolean hasEmptyCells() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (this.board.getCell(new BoardPosition(i, j)) == CellType.EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }

                CellType cell = this.board.getCell(new BoardPosition(i, j));
                System.out.print(cell == CellType.EMPTY ? "." : cell);
            }

            System.out.println();
        }
    }

    private Player getOpponent(Player player) {
        if (player == this.player1) {
            return this.player2;
        }
        else {
            return this.player1;
        }
    }

    private boolean equalCellsByOffset(int row, int col, int rowOffset, int colOffset) {
        CellType first = this.board.getCell(new BoardPosition(row, col));
        if (first == CellType.EMPTY) {
            return false;
        }

        for (int i = 1; i < Board.SIZE; i++) {
            CellType cell = this.board.getCell(new BoardPosition(row + i * rowOffset, col + i * colOffset));
            if (first != cell) {
                return false;
            }
        }

        return true;
    }

    private List<BoardPosition> getPossibleMovePositions() {
        if (this.isGameOver()) {
            return Collections.emptyList();
        }
        else {
            List<BoardPosition> possibleMoves = new ArrayList<>();
            for (int row = 0; row < Board.SIZE; row++) {
                for (int col = 0; col < Board.SIZE; col++) {
                    BoardPosition position = new BoardPosition(row, col);
                    if (this.board.isEmptyCell(position)) {
                        possibleMoves.add(position);
                    }
                }
            }

            return possibleMoves;
        }
    }

    private int evaluateScoreByOffsets(int row, int col, int rowOffset, int colOffset) {
        int opponentCells = 0;
        int myCells = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            CellType cellType = this.board.getCell(new BoardPosition(row + i * rowOffset, col + i * colOffset));
            if (cellType == this.player1.getCellType()) {
                opponentCells++;
            }
            else if (cellType == this.player2.getCellType()) {
                myCells++;
            }
        }

        return this.evaluateCellsCount(myCells, opponentCells);
    }

    private int evaluateCellsCount(int player1Cells, int player2Cells) {
        if (player1Cells == 3) {
            return 100;
        }
        else if (player1Cells == 2 && player2Cells == 0) {
            return 10;
        }
        else if (player1Cells == 1 && player2Cells == 0) {
            return 1;
        }
        else if (player2Cells == 3) {
            return -100;
        }
        else if (player2Cells == 2 && player1Cells == 0) {
            return -10;
        }
        else if (player2Cells == 1 && player1Cells == 0) {
            return 0;
        }
        else {
            return 0;
        }
    }

    private int evaluateScore() {
        int score = 0;
        for (int row = 0; row < Board.SIZE; row++) {
            score += this.evaluateScoreByOffsets(row, 0, 0, 1); // Rows
        }

        for (int col = 0; col < Board.SIZE; col++) {
            score += this.evaluateScoreByOffsets(0, col, 1, 0); // Cols
        }

        score += this.evaluateScoreByOffsets(0, 0, 1, 1); // First diagonal
        score += this.evaluateScoreByOffsets(Board.SIZE - 1, 0, -1, 1); // Second diagonal

        return score;
    }

    private GameMove miniMax(Player player, int alpha, int beta) {
        List<BoardPosition> possiblePositions = this.getPossibleMovePositions();
        if (possiblePositions.isEmpty()) {
            int score = this.evaluateScore();
            return new GameMove(new BoardPosition(-1, -1), score);
        }
        else {
            BoardPosition bestPosition = new BoardPosition(-1, -1);

            for (BoardPosition position : possiblePositions) {
                board.markCell(player.getCellType(), position);

                if (player.getPlayerType() == PlayerType.COMPUTER) {
                    GameMove move = this.miniMax(this.getOpponent(player), alpha, beta);
                    if (move.getScore() > alpha) {
                        alpha = move.getScore();
                        bestPosition = position;
                    }
                }
                else {
                    GameMove move = this.miniMax(this.getOpponent(player), alpha, beta);
                    if (move.getScore() < beta) {
                        beta = move.getScore();
                        bestPosition = position;
                    }
                }

                board.clearCell(position);
                if (alpha >= beta) {
                    break; // pruning
                }
            }

            if (player.getPlayerType() == PlayerType.COMPUTER) {
                return new GameMove(bestPosition, alpha);
            }
            else {
                return new GameMove(bestPosition, beta);
            }
        }
    }
}
