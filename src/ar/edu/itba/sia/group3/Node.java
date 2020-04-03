package ar.edu.itba.sia.group3;

public class Node{
    private State state;
    private Node parent;
    private int cost;
    private int depth;

    public Node(State state, Node parent, int cost, int depth) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.depth = depth;
    }

    public Node(State state) {
        this.state = state;
        this.parent = null;
        this.cost = 0;
        this.depth = 0;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
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


