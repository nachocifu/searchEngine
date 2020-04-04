package ar.edu.itba.sia.group3;

public class Distances {

    public static int manhattanDistance(Position p1,Position p2){
        return Math.abs(p2.row-p1.row) + Math.abs(p2.column-p1.column);
    }

    public static int euclideanDistance(Position p1,Position p2){
        return (int) Math.hypot(Math.abs(p2.row - p1.row),Math.abs(p2.column - p1.column));
    }
}
