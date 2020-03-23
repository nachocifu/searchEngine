package ar.edu.itba.sia.group3;

import java.util.Set;

public class Board implements Cloneable{

    private Cell[][] board = null;
    private int rows, columns;
    private int playerX;
    private int playerY;

    public Board(Cell[][] board, int rows, int columns, int playerX, int playerY) {
        this.board = board;
        this.rows = rows;
        this.columns = columns;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void print(){
        //TODO imprimir en consola el board
    }
}
