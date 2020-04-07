package ar.edu.itba.sia.group3;

public class ManhattanDistanceTargetsToBoxes implements Heuristic {
    @Override
    public Integer calculate(State state) {
        SokobanState sokobanState = (SokobanState) state;

       /* if(DeadlockFinder.isDeadlock(sokobanState)){
            return Integer.MAX_VALUE;
        } */

        if(sokobanState.getHeuristic() == null) {
            int acum = 0;
            int currManhattanDistance;
            int minManhattanDistance;
            for(Position box : sokobanState.getBoxes()){
                minManhattanDistance = Integer.MAX_VALUE;
                for(Position target : sokobanState.getTargets()){
                    currManhattanDistance = Distances.manhattanDistance(target,box);
                    if(currManhattanDistance < minManhattanDistance){
                        minManhattanDistance = currManhattanDistance;
                    }
                }
                acum += minManhattanDistance;
            }
            sokobanState.setHeuristic(acum);
            return acum;
        } else
            return sokobanState.getHeuristic();
    }

    @Override
    public String toString() { return ManhattanDistanceTargetsToBoxes.class.getSimpleName(); }
}
