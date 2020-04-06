package ar.edu.itba.sia.group3;

public class ManhattanDistancePlayerToBoxes implements Heuristic {
    @Override
    public Integer calculate(State state) {
        SokobanState sokobanState = (SokobanState) state;
        int manhattanDistance = 0;
        for(Position p : ((SokobanState) state).getBoxes()){
                manhattanDistance += Distances.manhattanDistance(p,((SokobanState) state).getPlayerPosition());
        }
        return manhattanDistance;
    }
}
