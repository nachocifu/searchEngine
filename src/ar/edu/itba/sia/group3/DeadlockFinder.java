package ar.edu.itba.sia.group3;

public class DeadlockFinder {

    public static boolean isDeadlock(SokobanState state){
        for(Position box : state.getBoxes()){
            if(!isNotFrozen(state.getBoard(),box)){
                return false;
            }
        }
        return true;
    }

    private static boolean isNotFrozen(Cell[][] board,Position box){
            //check both axis
            return checkFrozenVertical(board,box) && checkFrozenHorizontal(board,box);
    }


    private static boolean checkFrozenHorizontal(Cell[][] board,Position box){
        //if none is wall
        if(!board[box.row][box.column+1].isWall() && !board[box.row][box.column-1].isWall()){
            //it can be a box
            if(board[box.row][box.column+1].hasBox() && !board[box.row][box.column-1].hasBox()){
                //need to check if right box is frozen on vertical axis, since its frozen in horizontal
                return checkFrozenVertical(board,new Position(box.row,box.column+1));
            }
            if(!board[box.row][box.column+1].hasBox() && board[box.row][box.column-1].hasBox()){
                //need to check if left box is frozen, since its frozen in horizontal
                return checkFrozenVertical(board,new Position(box.row,box.column-1));
            }
            if(board[box.row][box.column-1].hasBox() && board[box.row][box.column+1].hasBox()){
                //need to check if either is frozen
                return checkFrozenVertical(board,new Position(box.row,box.column-1)) && checkFrozenVertical(board,new Position(box.row,box.column+1));
            }
            //empty spaces, not frozen
            return true;
        }
        //if there is a wall, then its horizontally blocked
        else{
            return false;
        }
    }

    private static boolean checkFrozenVertical(Cell[][] board,Position box){
        if(!board[box.row+1][box.column].isWall() && !board[box.row-1][box.column].isWall()){
            if(board[box.row+1][box.column].hasBox() && !board[box.row-1][box.column].hasBox()){
                return checkFrozenHorizontal(board,new Position(box.row+1,box.column));
            }
            if(!board[box.row+1][box.column].hasBox() && board[box.row-1][box.column].hasBox()){
                return checkFrozenHorizontal(board,new Position(box.row-1,box.column));
            }
            if(board[box.row-1][box.column].hasBox() && board[box.row+1][box.column].hasBox()){
                return checkFrozenHorizontal(board,new Position(box.row-1,box.column)) && checkFrozenHorizontal(board,new Position(box.row+1,box.column));
            }
            return true;
        }
        else {
            return false;
        }
    }
}
