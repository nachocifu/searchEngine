package ar.edu.itba.sia.group3;

public class DeadlockFinder {

    public static boolean isDeadlock(SokobanState state){
        for(Position box : state.getBoxes()){
            if(isFrozen(state.getBoard(),box)){
                return true;
            }
        }
        return false;
    }

    private static boolean isFrozen(Cell[][] board, Position box){
            //check both axis
            return isFrozenVertical(board,box) && isFrozenHorizontal(board,box);
    }


    private static boolean isFrozenHorizontal(Cell[][] board, Position box){
        //if none is wall
        if(!board[box.row][box.column+1].isWall() && !board[box.row][box.column-1].isWall()){
            //it can be a box
            if(board[box.row][box.column+1].hasBox() && !board[box.row][box.column-1].hasBox()){
                //need to check if right box is frozen on vertical axis, since its frozen in horizontal
                return isFrozenVertical(board,new Position(box.row,box.column+1));
            }
            if(!board[box.row][box.column+1].hasBox() && board[box.row][box.column-1].hasBox()){
                //need to check if left box is frozen, since its frozen in horizontal
                return isFrozenVertical(board,new Position(box.row,box.column-1));
            }
            if(board[box.row][box.column-1].hasBox() && board[box.row][box.column+1].hasBox()){
                //need to check if either is frozen
                return isFrozenVertical(board,new Position(box.row,box.column-1)) && isFrozenVertical(board,new Position(box.row,box.column+1));
            }
            //empty spaces, not frozen
            return false;
        }
        //if there is a wall, then its horizontally blocked
        else{
            return true;
        }
    }

    private static boolean isFrozenVertical(Cell[][] board, Position box){
        if(!board[box.row+1][box.column].isWall() && !board[box.row-1][box.column].isWall()){
            if(board[box.row+1][box.column].hasBox() && !board[box.row-1][box.column].hasBox()){
                return isFrozenHorizontal(board,new Position(box.row+1,box.column));
            }
            if(!board[box.row+1][box.column].hasBox() && board[box.row-1][box.column].hasBox()){
                return isFrozenHorizontal(board,new Position(box.row-1,box.column));
            }
            if(board[box.row-1][box.column].hasBox() && board[box.row+1][box.column].hasBox()){
                return isFrozenHorizontal(board,new Position(box.row-1,box.column)) && isFrozenHorizontal(board,new Position(box.row+1,box.column));
            }
            return false;
        }
        else {
            return true;
        }
    }
}
