package ar.edu.itba.sia.group3;

public class ManhattanDistancePlayerToBoxes implements Heuristic {
    @Override
    public Integer calculate(State state) {
        SokobanState sokobanState = (SokobanState) state;

      /*  if(DeadlockFinder.isDeadlock(sokobanState)){
            return Integer.MAX_VALUE;
        } */


        if(sokobanState.getHeuristic()==null) {
            int manhattanDistance = 0;
            if(!state.isDone())
                for (Position p : ((SokobanState) state).getBoxes()) {
                    manhattanDistance += Distances.manhattanDistance(p, ((SokobanState) state).getPlayerPosition());
                }
            sokobanState.setHeuristic(manhattanDistance);
            return manhattanDistance;
        } else
            return sokobanState.getHeuristic();
    }

    @Override
    public String toString() { return ManhattanDistancePlayerToBoxes.class.getSimpleName(); }
}
