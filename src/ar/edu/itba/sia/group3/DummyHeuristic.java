package ar.edu.itba.sia.group3;

import java.util.Set;

public class DummyHeuristic implements Heuristic {

    @Override
    public int getValue(State current) { return current.isDone()? 0:1; }

}
