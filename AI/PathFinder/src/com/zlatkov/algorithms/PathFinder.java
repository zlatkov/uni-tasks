package com.zlatkov.algorithms;

import com.zlatkov.BoardState;

public abstract class PathFinder {
    private final BoardState initialState;

    public PathFinder(BoardState initialState) {
        this.initialState = initialState;
    }

    public BoardState getInitialState() {
        return this.initialState;
    }

    public abstract BoardState findPath();
}
