package com.zlatkov;

public class GameMove {
    private final BoardPosition position;
    private final int score;

    public GameMove(BoardPosition position, int score) {
        this.position = position;
        this.score = score;
    }

    public BoardPosition getPosition() { return this.position; }

    public int getScore() { return this.score; }
}
