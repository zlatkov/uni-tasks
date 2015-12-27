package com.zlatkov.algorithms;

import com.zlatkov.*;

import java.util.*;

public class AStart extends PathFinder {

    public AStart(BoardState initialState) {
        super(initialState);
    }

    @Override
    public BoardState findPath() {
        Queue<Node> queue = new PriorityQueue<>();
        Set<BoardState> visited = new HashSet<>();

        queue.add(new Node(this.getInitialState(), 0));
        visited.add(this.getInitialState());
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            BoardState state = node.getState();

            if (BoardStateManager.isFinal(state)) {
                return state;
            }
            else {
                List<BoardState> adjacentStates = BoardStateManager.generateAdjacent(state);
                for (BoardState adjacentState : adjacentStates) {
                    if (!visited.contains(adjacentState)) {
                        visited.add(adjacentState);
                        int estimatedDistance = node.getDistance() + Heuristics.calculateManhattanDistance(adjacentState);
                        queue.add(new Node(adjacentState, estimatedDistance));
                    }
                }
            }
        }

        return null;
    }
}
