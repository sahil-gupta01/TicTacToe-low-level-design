package TicTacToe.winningStrategies;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategies{
    Map<Character,Integer> leftDiag = new HashMap<>();
    Map<Character,Integer> rightDiag = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {


        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        if(row == col){
            if(!leftDiag.containsKey(symbol)){
                leftDiag.put(symbol,0);
            }
            leftDiag.put(symbol,leftDiag.get(symbol)+1);
            if(leftDiag.get(symbol)==board.getDimension())return true;
        }
        if(row+col == board.getDimension()){
            if(!rightDiag.containsKey(symbol)){
                rightDiag.put(symbol,0);
            }
            rightDiag.put(symbol,rightDiag.get(symbol)+1);
            if(rightDiag.get(symbol)==board.getDimension())return true;
        }
        return false;
    }

    @Override
    public void performUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        if(row==col){
            leftDiag.put(symbol, leftDiag.get(symbol)-1);
        }
        if(row+col == board.getDimension()){
            rightDiag.put(symbol, rightDiag.get(symbol)-1);
        }
    }
}
