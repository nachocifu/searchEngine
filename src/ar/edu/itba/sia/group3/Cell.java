package ar.edu.itba.sia.group3;

import java.util.Objects;

public class Cell {
    private final CellType type;

    private boolean hasPlayer, hasBox;

    public Cell(CellType cellType) { type = cellType; }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean hasBox() {
        return hasBox;
    }

    public void setHasBox(boolean hasBox) {
        this.hasBox = hasBox;
    }

    public CellType getType() {
        return type;
    }

    public boolean isWall() {
        return type.equals(CellType.WALL);
    }

    public boolean isGoal() {
        return type.equals(CellType.GOAL);
    }

    public boolean canBlock() {
        return type==CellType.WALL|| hasBox;
    }

    @Override
    public Cell clone() {
        Cell newCell = new Cell(type);
        newCell.setHasBox(hasBox);
        newCell.setHasPlayer(hasPlayer);
        return newCell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return hasPlayer == cell.hasPlayer &&
                hasBox == cell.hasBox &&
                type == cell.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, hasPlayer, hasBox);
    }

    @Override
    public String toString() {
        if(hasBox && type ==CellType.GOAL) return "*";
        if(hasPlayer && type ==CellType.GOAL) return "+";
        if(type==CellType.FREE) {
            if(hasPlayer) return "@";
            if(hasBox) return "$";
        }
        return type.name;
    }
}
