package com.zlatkov.algorithms;

import com.zlatkov.BoardState;
import com.zlatkov.BoardStateManager;

import java.util.*;

public class Bfs extends PathFinder {

    public Bfs(BoardState initialState) {
        super(initialState);
    }

    @Override
    public BoardState findPath() {
        Queue<BoardState> states = new LinkedList<>();
        Set<BoardState> visitedStates = new HashSet<>();

        states.add(this.getInitialState());
        visitedStates.add(this.getInitialState());

        while (!states.isEmpty()) {
            BoardState state = states.remove();

            if (BoardStateManager.isFinal(state)) {
                return state;
            }
            else {
                List<BoardState> adjacentStates = BoardStateManager.generateAdjacent(state);
                for (BoardState adjacentState : adjacentStates) {
                    if (!visitedStates.contains(adjacentState)) {
                        visitedStates.add(adjacentState);
                        states.add(adjacentState);
                    }
                }
            }
        }

        return null;
    }
}
