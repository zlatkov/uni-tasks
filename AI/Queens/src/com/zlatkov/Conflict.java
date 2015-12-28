package com.zlatkov;

public class Conflict implements Comparable<Conflict>{
    private final int columnIndex;
    private final int count;

    public Conflict(int columnIndex, int count) {
        this.columnIndex = columnIndex;
        this.count = count;
    }

    public int getColumn() { return this.columnIndex; }

    public int getCount() { return this.count; }

    @Override
    public int compareTo(Conflict other) {
        return this.getCount() - other.getCount();
    }
}
