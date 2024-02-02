package TicTacToe.botPlayingStrategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
