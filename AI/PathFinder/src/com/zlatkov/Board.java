package com.zlatkov;

public class Board {
    public static final int SIZE = 3;

    public static final int EMPTY_CELL = 0;

    public static final BoardPosition[] DELTA_POSITIONS = new BoardPosition[] {
            new BoardPosition(-1, 0),
            new BoardPosition(1, 0),
            new BoardPosition(0, -1),
            new BoardPosition(0, 1)
    };
}
