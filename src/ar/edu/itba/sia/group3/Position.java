package ar.edu.itba.sia.group3;

import java.util.Objects;

public class Position{
    public final int row, column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public Position add(Position delta) {
        return new Position(row + delta.row, column + delta.column);
    }
}
