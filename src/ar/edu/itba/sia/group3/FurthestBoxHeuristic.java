package ar.edu.itba.sia.group3;

import java.util.Set;

/*
 Manhattan Distance: Boxes, X's A basic heuristic which returns the minimal sum of distances between boxes and storage locations. This heuristic is consistent. It's also a lower bound to the numbers of moves to solve the problem.
 Manhattan Distance: Player, Box A basic heuristic which returns the minimal distance between player and any box. This heuristic is also consistent.
 consistent heuristic: if its estimate is always less than or equal to the estimated distance from any neighbouring vertex to the goal, plus the cost of reaching that neighbour.
 Euclidean distance
 Manhattan with Hungarian algorithm
 habria que agregar un metodo/clase mas que busque si el estado es un deadlock/ hay deadLock inminente para asignar Integer.MAX_VALUE
*/
public class FurthestBoxHeuristic implements Heuristic {

    @Override
    public Integer calculate(State current) {
        SokobanState state = (SokobanState)current;
        Set<Position> boxes = state.getBoxes();

        
        return 0;
    }

    @Override
    public String toString() { return DummyHeuristic.class.getSimpleName(); }

}
