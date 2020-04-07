package ar.edu.itba.sia.group3;

public class ManhattanDistancePlayerToBoxes implements Heuristic {
    @Override
    public Integer calculate(State state) {
        SokobanState sokobanState = (SokobanState) state;

        if(sokobanState.getHeuristic()==null) {
            int manhattanDistance = 0;
            for (Position p : ((SokobanState) state).getBoxes()) {
                manhattanDistance += Distances.manhattanDistance(p, ((SokobanState) state).getPlayerPosition());
            }
            sokobanState.setHeuristic(manhattanDistance);
            return manhattanDistance;
        } else
            return sokobanState.getHeuristic();
    }

    @Override
    public String toString() { return DummyHeuristic.class.getSimpleName(); }
}
