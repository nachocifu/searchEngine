package ar.edu.itba.sia.group3;

public class EuclideanDistanceTargetsToBoxes implements Heuristic {
    @Override
    public Integer calculate(State state) {
        SokobanState sokobanState = (SokobanState) state;

//        if(DeadlockFinder.isDeadlock(sokobanState)){
//           sokobanState.setHeuristic(Integer.MAX_VALUE);
//           System.out.println(sokobanState.getRepresentation());
//           return Integer.MAX_VALUE;
//        }

        if(sokobanState.getHeuristic()==null) {
            int acum = 0;
            int currManhattanDistance;
            int minManhattanDistance;
            for(Position box : sokobanState.getBoxes()){
                minManhattanDistance = Integer.MAX_VALUE;
                for(Position target : sokobanState.getTargets()){
                    currManhattanDistance = Distances.euclideanDistance(target,box);
                    if(currManhattanDistance < minManhattanDistance){
                        minManhattanDistance = currManhattanDistance;
                    }
                }
                acum += minManhattanDistance;
            }
            sokobanState.setHeuristic(acum);
            return acum;
        }
        return sokobanState.getHeuristic();
    }

    @Override
    public String toString() { return EuclideanDistanceTargetsToBoxes.class.getSimpleName(); }
}
