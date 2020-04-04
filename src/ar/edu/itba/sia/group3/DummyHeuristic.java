package ar.edu.itba.sia.group3;

public class DummyHeuristic implements Heuristic {

    @Override
    public Integer calculate(State current) {
        if(current.getHeuristic()==null) {
            current.setHeuristic(current.isDone()?0:1);
        }
        return current.getHeuristic();
    }

    @Override
    public String toString() { return DummyHeuristic.class.getSimpleName(); }
}
