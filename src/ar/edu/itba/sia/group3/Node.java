package ar.edu.itba.sia.group3;

public class Node{
    private State state;
    private Node parent;
    private int costFunction;
    private int depth;

    public Node(State state, Node parent, int costFunction, int depth) {
        this.state = state;
        this.parent = parent;
        this.costFunction = costFunction;
        this.depth = depth;
    }

    public Node(State state, int costFunction) {
        this.state = state;
        this.parent = null;
        this.costFunction = 0;
        this.depth = 0;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Integer getCostFunction() {
        return costFunction;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isDone(){
        return state.isDone();
    }

    public String toString(){
        return state.getRepresentation();
    }
}


