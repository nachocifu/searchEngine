package ar.edu.itba.sia.group3;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                return Algorithm.GREEDY;
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

    public static Heuristic getHeuristic(String arg){
        switch (arg) {
//            case "BFS":
//                return new BFS();
//            case "DFS":
//                return new DFS();
//            case "IDDFS":
//                return new IDDFS();
//            case "GGS":
//                return new GGS();
            default: return null;
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

        Heuristic heuristic = getHeuristic(args[1]);
/*        if(heuristic==null){
            System.err.println("Error reading heuristic method");
            return;
        } */



        Node root = new Node(state);

        // -----
        Searcher searcher = new Searcher(algorithm, heuristic, root);

        Node finalNode = searcher.run();

        if(finalNode == null) {
            System.err.println("Pincho ameooo. Tiene Solucion?");
        } else
            printNodePath(finalNode);

    }

    private static void printNodePath(Node node) {
        if(node == null) return;
        System.out.println(node);
        printNodePath(node.getParent());
    }

}
