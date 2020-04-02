package ar.edu.itba.sia.group3;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class SokobanState implements State, Cloneable {

    private Position playerPosition;
    private Set<Position> boxes;
    private Set<Position> targets;
    private Cell[][] board;


    public SokobanState(Cell[][] board) {
        this.board = board;
        // TODO parsear board y rellenar playerPosition, boxes y targets
    }

    /**
     * Provides the representation of the state so it can be printed on the solution representation.
     *
     * @return The STRING representation of the state.
     *
     */
    public String getRepresentation() {
        // TODO retornar un string con enters y todo que dibuje el tablero en la CLI
        return "";
    }


    public boolean isDone() {
        return boxes.stream().allMatch(box -> board[box.row][box.column].isGoal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SokobanState that = (SokobanState) o;
        return playerPosition.equals(that.playerPosition) &&
                boxes.equals(that.boxes) &&
                targets.equals(that.targets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerPosition, boxes, targets);
    }
}
