package TicTacToe.winningStrategies;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategies{
    Map<Integer, Map<Character, Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        if(!colMap.containsKey(col)){
            colMap.put(col, new HashMap<Character, Integer>());
        }
        if(!colMap.get(col).containsKey(symbol)){
            colMap.get(col).put(symbol,0);
        }
        colMap.get(col).put(symbol,colMap.get(col).get(symbol)+1);
        if(colMap.get(col).get(symbol)==board.getDimension())return true;
        return false;
    }

    @Override
    public void performUndo(Board board,Move move) {
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();
        Map<Character,Integer> currMap =  colMap.get(col);
        currMap.put(symbol, currMap.get(symbol)-1);
    }


}
