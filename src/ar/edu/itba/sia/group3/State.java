package ar.edu.itba.sia.group3;

import java.util.List;

public abstract class State {

    private Integer heuristic = null;

    public Integer getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Integer heuristic) {
        this.heuristic = heuristic;
    }

    public abstract boolean isDone();

    public abstract List<State> explode() throws CloneNotSupportedException;

    public abstract String getRepresentation();
}
