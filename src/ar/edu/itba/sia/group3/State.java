package ar.edu.itba.sia.group3;

import java.util.List;

public interface State {
    boolean isDone();

    List<State> explode() throws CloneNotSupportedException;

    String getRepresentation();
}
