package TicTacToe.Models;

import TicTacToe.botPlayingStrategy.BotPlayingStrategy;
import TicTacToe.botPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(char symbol, String name, PlayerType type, int id, BotDifficultyLevel botDifficultyLevel) {
        super(symbol, name, type, id);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifferentDifficulty(botDifficultyLevel);
    }
    public Cell makeMove(Board board) throws InterruptedException {
        System.out.println("Wait for the "+this.getName() +"'s turn");
        Thread.sleep(2000);
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setState(CellState.FILL);
        cell.setPlayer(this);
        return cell;
    }
}
