package ar.edu.itba.sia.group3;

import java.util.Set;

public class FurthestBoxHeuristic implements Heuristic {

    @Override
    public Integer calculate(State current) {
        SokobanState state = (SokobanState)current;
        Set<Position> boxes = state.getBoxes();

        
        return 0;
    }

}
