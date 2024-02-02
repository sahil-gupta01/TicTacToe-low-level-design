package TicTacToe.winningStrategies;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.Move;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategies{
    Map<Integer, Map<Character, Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();

        if(!rowMap.containsKey(row)){
            rowMap.put(row, new HashMap<>());
        }
        if(!rowMap.get(row).containsKey(symbol)){
            rowMap.get(row).put(symbol,0);
        }
        rowMap.get(row).put(symbol,rowMap.get(row).get(symbol)+1);
        if(rowMap.get(row).get(symbol)==board.getDimension())return true;
        return false;
    }

    @Override
    public void performUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();
        Map<Character,Integer> currMap =  rowMap.get(row);
        currMap.put(symbol, currMap.get(symbol)-1);
    }
}
