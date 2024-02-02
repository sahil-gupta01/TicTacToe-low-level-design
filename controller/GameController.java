package TicTacToe.controller;

import TicTacToe.Models.Game;
import TicTacToe.Models.Player;
import TicTacToe.exception.InvalidPlayersOrBoardSizeException;
import TicTacToe.exception.MoreThanOneBotException;
import TicTacToe.exception.NotUniqueSymbolsException;
import TicTacToe.winningStrategies.WinningStrategies;

import java.util.List;

public class GameController {
    public Game startGame(int dimensions, List<Player> players, List<WinningStrategies> winningStrategies) throws MoreThanOneBotException, NotUniqueSymbolsException, InvalidPlayersOrBoardSizeException {
        return Game.createBuilder()
                .setDimension(dimensions)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game) throws InterruptedException {
        game.makeMove();
    }
    public void undo(Game game){
        game.performUndo();
    }
}
