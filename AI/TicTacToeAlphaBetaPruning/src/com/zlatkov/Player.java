package com.zlatkov;

public class Player {
    private final PlayerType playerType;
    private final CellType cellType;

    public Player(PlayerType playerType, CellType cellType) {
        this.playerType = playerType;
        this.cellType = cellType;
    }

    public PlayerType getPlayerType() { return this.playerType; }

    public CellType getCellType() { return this.cellType; }
}
