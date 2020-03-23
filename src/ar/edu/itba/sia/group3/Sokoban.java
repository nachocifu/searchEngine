package ar.edu.itba.sia.group3;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sokoban {

    public static SearchMethod getSearchMethod(String input) {
        switch (input) {
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

    /**
     * Generates board from string
     * @param input A string representation of the input file
     * @return A grid of chars
     */
    public static Cell[][] getBoard(String input){

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
                    case '.': board[i][j] = new Cell(CellType.GOAL); break;
                    default: board[i][j] = new Cell(CellType.FREE); break;
                }

            }
        }
        return board;
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
        try {
            input = String.join("\n", Files.readAllLines(path));
            board = getBoard(input);
        } catch (IOException e) {
            System.err.println("Error reading input FIle. => " + e.getMessage());
            return;
        }

        SearchMethod algorithm = getSearchMethod(args[1]);
        if(algorithm==null){
            System.err.println("Error reading search method");
            return;
        }

        Heuristic heuristic = getHeuristic(args[1]);
        if(heuristic==null){
            System.err.println("Error reading heuristic method");
            return;
        }

        // -----
        Searcher searcher = new Searcher(algorithm, heuristic, root);

        NodeInterface finalNode = searcher.run();

        if(finalNode == null) {
            System.err.println("Pincho ameooo");
        } else
            printNodePath(finalNode);

    }

    public static void printNodePath(NodeInterface node) {
        if(node == null) return;
        node.getBoard().print();
        return printNodePath(node.getParent());
    }

}
