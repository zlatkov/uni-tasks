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
        Map<BoardState, Integer> realDistances = new HashMap<>();

        queue.add(new Node(this.getInitialState(), Heuristics.calculateManhattanDistance(this.getInitialState())));
        realDistances.put(this.getInitialState(), 0);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            BoardState state = node.getState();

            if (BoardStateManager.isFinal(state)) {
                return state;
            }
            else {
                List<BoardState> adjacentStates = BoardStateManager.generateAdjacent(state);
                for (BoardState adjacentState : adjacentStates) {
                    int distance = realDistances.get(state) + 1;
                    if (!realDistances.containsKey(adjacentState) || realDistances.get(adjacentState) > distance) {
                        realDistances.put(adjacentState, distance);
                        queue.add(new Node(adjacentState, distance + Heuristics.calculateManhattanDistance(adjacentState)));
                    }
                }
            }
        }

        return null;
    }
}
