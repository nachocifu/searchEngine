package ar.edu.itba.sia.group3;

import java.util.*;

public class SokobanState extends State implements Cloneable {

    private Position playerPosition;
    private Set<Position> boxes;
    private Set<Position> targets;
    private Cell[][] board;

    public Set<Position> getBoxes() {
        return boxes;
    }

    public Set<Position> getTargets() {
        return targets;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public SokobanState(Cell[][] board, Position playerPosition, Set<Position> boxes, Set<Position> targets) {
        this.board = board;
        this.playerPosition = playerPosition;
        this.targets = targets;
        this.boxes = boxes;
    }

    /**
     * Provides the representation of the state so it can be printed on the solution representation.
     *
     * @return The STRING representation of the state.
     */
    public String getRepresentation() {
        StringBuilder representation = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                representation.append(board[i][j].toString());
            }
            representation.append('\n');
        }
        return representation.toString();
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

    public SokobanState clone() throws CloneNotSupportedException {
        SokobanState sokobanState = (SokobanState) super.clone();
        Cell[][] newBoard = new Cell[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[i][j] = board[i][j].clone();
            }
        }
        sokobanState.board = newBoard;
        sokobanState.boxes = new HashSet<>(boxes);
        sokobanState.targets = new HashSet<>(targets);
        return sokobanState;
    }


    @Override
    public int hashCode() {
        return Objects.hash(playerPosition, boxes, targets);
    }


    public List<State> explode() throws CloneNotSupportedException {
        List<State> performableActions = new LinkedList<>();
        SokobanState aux = null;

        //movement down to empty cell
        if (!board[playerPosition.row + 1][playerPosition.column].isWall() && !board[playerPosition.row + 1][playerPosition.column].hasBox()) {
            aux = this.clone();
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(1, 0));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            performableActions.add(aux);
        }
        //movement down to cell with box
        if (board[playerPosition.row + 1][playerPosition.column].hasBox() && playerPosition.row+2 < board.length && !board[playerPosition.row + 2][playerPosition.column].hasBox()
                && !board[playerPosition.row + 2][playerPosition.column].isWall()) {
            aux = this.clone();
            //move player
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(1, 0));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            //move box
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasBox(false);
            aux.board[aux.playerPosition.row + 1][aux.playerPosition.column].setHasBox(true);
            aux.boxes.remove(new Position(aux.playerPosition.row, aux.playerPosition.column));
            aux.boxes.add(new Position(aux.playerPosition.row + 1, aux.playerPosition.column));
            performableActions.add(aux);
        }
        //movement up to empty cell
        if (!board[playerPosition.row - 1][playerPosition.column].isWall() && !board[playerPosition.row - 1][playerPosition.column].hasBox()) {
            aux = this.clone();
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(-1, 0));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            performableActions.add(aux);
        }
        //movement up to cell with box
        if (board[playerPosition.row - 1][playerPosition.column].hasBox() && playerPosition.row-2 >= 0 && !board[playerPosition.row - 2][playerPosition.column].hasBox()
                && !board[playerPosition.row - 2][playerPosition.column].isWall()) {
            aux = this.clone();
            //move player
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(-1, 0));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            //move box
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasBox(false);
            aux.board[aux.playerPosition.row - 1][aux.playerPosition.column].setHasBox(true);
            aux.boxes.remove(new Position(aux.playerPosition.row, aux.playerPosition.column));
            aux.boxes.add(new Position(aux.playerPosition.row - 1, aux.playerPosition.column));
            performableActions.add(aux);
        }
        //movement left to empty cell
        if (!board[playerPosition.row][playerPosition.column - 1].isWall() && !board[playerPosition.row][playerPosition.column - 1].hasBox()) {
            aux = this.clone();
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(0, -1));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            performableActions.add(aux);
        }
        //movement left to cell with box
        if (board[playerPosition.row][playerPosition.column - 1].hasBox() && playerPosition.column-2 >= 0 && !board[playerPosition.row][playerPosition.column - 2].hasBox()
                && !board[playerPosition.row][playerPosition.column - 2].isWall()) {
            aux = this.clone();
            //move player
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(0, -1));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            //move box
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasBox(false);
            aux.board[aux.playerPosition.row][aux.playerPosition.column-1].setHasBox(true);
            aux.boxes.remove(new Position(aux.playerPosition.row, aux.playerPosition.column));
            aux.boxes.add(new Position(aux.playerPosition.row, aux.playerPosition.column - 1));
            performableActions.add(aux);
        }

        //movement right to empty cell
        if (!board[playerPosition.row][playerPosition.column + 1].isWall() && !board[playerPosition.row][playerPosition.column + 1].hasBox()) {
            aux = this.clone();
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(0, 1));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            performableActions.add(aux);
        }
        //movement right to cell with box
        if (board[playerPosition.row][playerPosition.column + 1].hasBox() && playerPosition.column+2 < board[0].length && !board[playerPosition.row][playerPosition.column + 2].hasBox()
                && !board[playerPosition.row][playerPosition.column + 2].isWall()) {
            aux = this.clone();
            //move player
            aux.board[playerPosition.row][playerPosition.column].setHasPlayer(false);
            aux.playerPosition = aux.playerPosition.add(new Position(0, 1));
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasPlayer(true);
            //move box
            aux.board[aux.playerPosition.row][aux.playerPosition.column].setHasBox(false);
            aux.board[aux.playerPosition.row][aux.playerPosition.column + 1].setHasBox(true);
            aux.boxes.remove(new Position(aux.playerPosition.row, aux.playerPosition.column));
            aux.boxes.add(new Position(aux.playerPosition.row, aux.playerPosition.column + 1));
            performableActions.add(aux);
        }
        return performableActions;
    }
}
