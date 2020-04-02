package ar.edu.itba.sia.group3;

import java.util.*;

public class Searcher {


    private Algorithm algorithm;
    private Heuristic heuristic;
    private Node root;
    private PriorityQueue<Node> frontier;
    private Set<State> passStates;


    /**
     * Loads de searcher
     * @param algorithm
     * @param heuristic
     * @param root
     */
    public Searcher(Algorithm algorithm, Heuristic heuristic, Node root) {
        this.algorithm = algorithm;
        this.heuristic = heuristic;
        this.root = root;
        passStates = new HashSet<>();
        frontier = new PriorityQueue<Node>(AlgorithmsFactory.getComparator(algorithm)); // TODO based on algorithm and heuristic should have at the top the next node to explode always
    }

    /**
     *
     * @return
     */
    public Node run(){
        Node current;
        List<State> result;
        frontier.offer(root);
        // TODO un while que
        while (!frontier.isEmpty()) {
            current = frontier.poll();
            System.out.println(current.print());
            if(current.isDone()){
                return current;
            }
            try {
                result = current.getState().explode();
            } catch (CloneNotSupportedException e) {
                System.err.println(e.getMessage());
                continue;
            }
            for(State s : result){
                if(!passStates.contains(s)){
                    passStates.add(s);
                    frontier.add(new Node(s,current,1,current.getDepth()+1));
                }
            }
        }
        return null;
    }



    /*private List<NodeInterface> explode(NodeInterface) {

    }*/
}
