package ar.edu.itba.sia.group3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Sokoban {

    public static Algorithm getSearchMethod(String input) {
        switch (input) {
            case "BFS":
                return Algorithm.BFS;
            case "DFS":
                return Algorithm.DFS;
            case "IDDFS":
                return Algorithm.IDDFS;
            case "GGS":
                return Algorithm.GGS;
            default: return null;
        }
    }

    /**
     * Generates board from string
     * @param input A string representation of the input file
     * @return A grid of chars
     */
    public static SokobanState getInitialState(String input){
        Position playerPosition = null;
        Set<Position> boxes = new HashSet<>();
        Set<Position> targets = new HashSet<>();

        String[] lines;
        lines = input.split("\n");
        int rows = lines.length;
        int columns = lines[0].length();
        Cell[][] board = new Cell[rows][columns];

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < columns; j++) {
                switch (line.charAt(j)) {
                    case '#': board[i][j] = new Cell(CellType.WALL); break;
                    case '.':
                        board[i][j] = new Cell(CellType.GOAL);
                        targets.add(new Position(i,j));
                        break;
                    case '$': board[i][j] = new Cell(CellType.FREE);
                        boxes.add(new Position(i,j));
                        board[i][j].setHasBox(true);
                        break;
                    case '@':
                        board[i][j] = new Cell(CellType.FREE);
                        playerPosition = new Position(i,j);
                        board[i][j].setHasPlayer(true);
                        break;
                    default: board[i][j] = new Cell(CellType.FREE); break;
                }
            }
        }
        return new SokobanState(board,playerPosition,boxes,targets);
    }

    public static Heuristic getHeuristic(String[] arg){

        switch (arg[1]) {
            case "BFS":
            case "DFS":
            case "IDDFS":
                return new DummyHeuristic();
            default:
                switch (arg[2]) {
                    case "dummy": return new DummyHeuristic();
                    default:
                        System.err.println("Error reading heuristic method");
                        throw new InvalidParameterException("Missing valid heuristic");
                }
        }
    }

    public static void main(String[] args) {

        // Get Board
        Path path = Paths.get(args[0]);
        String input = null;
        Cell[][] board;
        SokobanState state;

        try {
            input = String.join("\n", Files.readAllLines(path));
            state = getInitialState(input);
        } catch (IOException e) {
            System.err.println("Error reading input FIle. => " + e.getMessage());
            return;
        }

        Algorithm algorithm = getSearchMethod(args[1]);
        if(algorithm==null){
            System.err.println("Error reading search method");
            return;
        }


        Heuristic heuristic = getHeuristic(args);

        Node root = new Node(state, heuristic.calculate(state));

        // -----
        Searcher searcher = new Searcher(algorithm, heuristic, root);

        searcher.run();

        printReport(searcher);
    }

    private static void printReport(Searcher searcher) {
        printNodePath(searcher.getFinalNode());
        System.out.println("###################################");
        System.out.println("###################################");
        System.out.println("");
        System.out.println("# Parameters");
        System.out.println("Heuristic: "+ searcher.getHeuristic());
        System.out.println("Algorithm: "+ searcher.getAlgorithm());
        System.out.println("");
        System.out.println("# Results");
        if(searcher.getFinalNode()==null)
            System.err.print("Success: NO");
        else
            System.out.println("Success: YES");
        System.out.println("Time (mili): "+searcher.getExecutionTime());
        System.out.println("Depth: "+(searcher.getFinalNode()!=null?searcher.getFinalNode().getDepth():"(Doesnt Apply)"));
        System.out.println("Cost: "+getCostIfApplies(searcher));
        System.out.println("Num. Exploded Nodes: "+searcher.getExplodedCounter());
        System.out.println("Final frontier size: "+searcher.getFrontierSize());
    }

    private static String getCostIfApplies(Searcher searcher) {
        switch (searcher.getAlgorithm()) {
            case BFS:
            case DFS:
            case IDDFS:
                return "(Doesnt apply)";
            default:
                return searcher.getFinalNode()!=null?searcher.getFinalNode().getCost().toString():"(Doesnt apply)";
        }
    }

    private static void printNodePath(Node node) {
        if(node == null) return;
        System.out.println(node);
        printNodePath(node.getParent());
    }

}
