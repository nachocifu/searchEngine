package ar.edu.itba.sia.group3;

import java.util.Comparator;

public class AlgorithmsFactory {
    public static Comparator<Node> getComparator(Algorithm algorithm){
        switch(algorithm){

            case BFS: return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.getDepth() - o2.getDepth();
                }
            };

            case DFS: return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.getDepth() - o1.getDepth();
                }
            };

            case IDDFS: return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.getDepth() - o1.getDepth();
                }
            };

            default: return null;
        }
    }
}
