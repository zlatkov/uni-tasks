package com.zlatkov.algorithms;

import com.zlatkov.BoardState;
import com.zlatkov.BoardStateManager;
import com.zlatkov.Heuristics;
import com.zlatkov.Node;

import java.util.*;

public class BeamSearch extends PathFinder{
    private final int beamSize;

    public BeamSearch(BoardState initialState, int beamSize) {
        super(initialState);

        this.beamSize = beamSize;
    }

    @Override
    public BoardState findPath() {
        if (BoardStateManager.isFinal(this.getInitialState())) {
            return this.getInitialState();
        }

        Set<BoardState> visited = new HashSet<>();
        visited.add(this.getInitialState());
        List<BoardState> beam = new LinkedList<>();
        beam.add(this.getInitialState());

        while (!beam.isEmpty()) {
            Queue<Node> level = new PriorityQueue<>();
            for (BoardState state : beam) {
                List<BoardState> adjacentStates = BoardStateManager.generateAdjacent(state);
                for (BoardState adjacentState : adjacentStates) {
                    if (BoardStateManager.isFinal(adjacentState)) {
                        return adjacentState;
                    }

                    level.add(new Node(adjacentState, Heuristics.calculateManhattanDistance(adjacentState)));
                }
            }

            beam.clear();
            while (!level.isEmpty() && beam.size() < this.beamSize) {
                Node node = level.remove();
                if (!visited.contains(node.getState())) {
                    visited.add(node.getState());
                    beam.add(node.getState());
                }
            }
        }

        return null;
    }
}
