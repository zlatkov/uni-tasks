package com.zlatkov;

public class Node implements Comparable<Node> {
    private final BoardState state;
    private final int distance;

    public Node(BoardState state, int distance) {
        this.state = state;
        this.distance = distance;
    }

    public BoardState getState() { return this.state; }

    public int getDistance() { return this.distance; }

    @Override
    public int compareTo(Node node) {
        return this.distance - node.distance;
    }
}
