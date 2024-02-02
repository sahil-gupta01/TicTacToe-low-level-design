package TicTacToe.botPlayingStrategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row:board.getBoard()){
            for(Cell currCell:row){
                if(CellState.EMPTY.equals(currCell.getState())){
                    return currCell;
                }
            }
        }
        return null;
    }
}
