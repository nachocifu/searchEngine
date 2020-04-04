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

        Set<Node> iterativeAux = new HashSet<>(); //en los iterables guardo aca nodos que no debo seguir explorando en esa iteracion
        int limit = 1;
        iterativeAux.add(root);
        passStates.add(root.getState());

        Node current;
        List<State> result;
       // frontier.offer(root);

        //lo iterativo deberia ser transparente al resto
        while (!iterativeAux.isEmpty()){
            limit = limit*2;
            frontier.addAll(iterativeAux); //le doy a la cola nodo a partir de los cuales explorar. despues de primera iteracion son los que fueron limitados
            iterativeAux.clear(); //limpio para dejar los nuevos limitados

            while (!frontier.isEmpty()) {
                current = frontier.poll();
//                System.out.println(current.getState().getRepresentation());;
                if(current.isDone()){
                    return current;
                }
                if((algorithm == Algorithm.IDDFS || algorithm == Algorithm.IDASTAR) && (current.getDepth() == limit)) {
                    iterativeAux.add(current);
                } else {
                    try {
                        result = current.getState().explode();
                    } catch (CloneNotSupportedException e) {
                        System.err.println(e.getMessage());
                        continue;
                    }
                    for(State s : result){
                        if(!passStates.contains(s)){
                            passStates.add(s);
                            ;
                            frontier.add(new Node(s,current, current.getCost()+heuristic.calculate(s),current.getDepth()+1));
                        }
                    }
                }
            }
        }

        return null;
    }



    /*private List<NodeInterface> explode(NodeInterface) {

    }*/
}
