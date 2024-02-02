package TicTacToe.appRunner;

import Inheritance2.C;
import TicTacToe.Models.*;
import TicTacToe.controller.GameController;
import TicTacToe.exception.InvalidPlayersOrBoardSizeException;
import TicTacToe.exception.MoreThanOneBotException;
import TicTacToe.exception.NotUniqueSymbolsException;
import TicTacToe.winningStrategies.ColumnWinningStrategy;
import TicTacToe.winningStrategies.DiagonalWinningStrategy;
import TicTacToe.winningStrategies.RowWinningStrategy;
import TicTacToe.winningStrategies.WinningStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws NotUniqueSymbolsException, InvalidPlayersOrBoardSizeException, MoreThanOneBotException, InterruptedException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        int dimension = 3;

        List<Player> players = new ArrayList<>();
        players.add(new Player('X',"Sahil", PlayerType.HUMAN, 1));
        players.add(new Bot('O',"Robo", PlayerType.BOT, 2, BotDifficultyLevel.EASY));
        //players.add(new Player('0',"Shreya", PlayerType.HUMAN, 2));


        List<WinningStrategies> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(dimension, players,winningStrategies);

        while(GameState.IN_PROG.equals(game.getGameState())){
            //ask whether to make move or want to perform undo

            gameController.printBoard(game);
            System.out.println("Do anyone wants to undo - y/n");
            String undoAction = scanner.next();

            if(undoAction.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            else if(undoAction.equalsIgnoreCase("n")){
                gameController.makeMove(game);
            }
            else{
                System.out.println("Choose a correct option");
            }
        }

        if(game.getGameState().equals(GameState.DRAW)){
            gameController.printBoard(game);
            System.out.println("Its a draw, try again :/ ");
        }
        if(game.getGameState().equals(GameState.SUCCESS)){
            gameController.printBoard(game);
            System.out.println(game.getWinner().getName() + " is the Winner..");
        }
    }
}
