package ar.edu.itba.sia.group3;

import java.util.*;


public class Searcher {


    private Algorithm algorithm;
    private Heuristic heuristic;
    private Node root;
    private PriorityQueue<Node> frontier;
    private Set<State> passStates;

    private long executionTime;
    private Node solution;
    private long explodedCounter;


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
        frontier = new PriorityQueue<Node>(AlgorithmsFactory.getComparator(algorithm));
        // metrics
        executionTime = -1;
        executionTime = 0;
    }

    /**
     *
     * @return
     */
    public Node run(){
        long startTime = System.currentTimeMillis();
        int limit = 1;
        passStates.add(root.getState());

        Node current;
        List<State> result;
        boolean done = false;
        //lo iterativo deberia ser transparente al resto
        while (!done){
            limit = limit*2;
            if((algorithm == Algorithm.IDDFS || algorithm == Algorithm.IDASTAR)) System.out.println("ID: "+limit);
            frontier.offer(root);
            passStates.clear();

            while (!frontier.isEmpty()) {
                current = frontier.poll();
//                System.out.println(current.getState().getRepresentation());;
                if(current.isDone()){
                    done = true;
                    this.solution = current;
                    this.executionTime = System.currentTimeMillis() - startTime;
                    return this.solution;
                }
                if((algorithm == Algorithm.IDDFS || algorithm == Algorithm.IDASTAR) && (current.getDepth() == limit)) {
                    clearVisited(current); //. hay que desvisitar
                } else {
                    try {
                        this.explodedCounter++;
                        result = current.getState().explode();
                    } catch (CloneNotSupportedException e) {
                        System.err.println(e.getMessage());
                        continue;
                    }
                    for(State s : result){ //si se agregan 0 (hoja) hay que desvisitar?
                        if(!passStates.contains(s)){
                            passStates.add(s);
                            frontier.add(new Node(s,current, current.getDepth()+1+heuristic.calculate(s),current.getDepth()+1));
                        }
                    }
                }
            }
        }
        this.executionTime = System.currentTimeMillis() - startTime;
        return null;
    }


    public Node getFinalNode() {
        return this.solution;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public Long getExplodedCounter() {
        return explodedCounter;
    }

    public Integer getFrontierSize() {
        return frontier.size();
    }

    private void clearVisited(Node current){
        if(current == null){
            return;
        }
        if(!frontier.contains(current)){
            passStates.remove(current.getState());
        }
        clearVisited(current.getParent());
    }



    /*private List<NodeInterface> explode(NodeInterface) {

    }*/
}
