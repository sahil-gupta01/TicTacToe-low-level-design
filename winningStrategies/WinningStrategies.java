package TicTacToe.winningStrategies;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.Move;

public interface WinningStrategies {
    boolean checkWinner(Board board, Move move);
    void performUndo(Board board, Move move);
}
