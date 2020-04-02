package ar.edu.itba.sia.group3;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Searcher {


    private Algorithm algorithm;
    private Heuristic heuristic;
    private NodeInterface root;
    private PriorityQueue<NodeInterface> border;
    private List<State> passStates;


    /**
     * Loads de searcher
     * @param algorithm
     * @param heuristic
     * @param root
     */
    public Searcher(Algorithm algorithm, Heuristic heuristic, NodeInterface root) {
        this.algorithm = algorithm;
        this.heuristic = heuristic;
        this.root = root;
        passStates = new LinkedList<>();
        border = new PriorityQueue<>(); // TODO based on algorithm and heuristic should have at the top the next node to explode always
    }

    /**
     *
     * @return
     */
    public NodeInterface run(){

        // TODO un while que
        while (!border.isEmpty()) {
            //popear,,,revisar si es solucion
            if(heuristic.getValue(node) == 0)
                return node;
            else
            //
            // ,,,,revisar si es un passState....y sino explotar,,,meter hijos en queue
        }

        return null;
    }



    private List<NodeInterface> explode(NodeInterface) {

    }
}
