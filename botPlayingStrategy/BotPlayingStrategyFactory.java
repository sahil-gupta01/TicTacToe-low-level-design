package TicTacToe.botPlayingStrategy;

import TicTacToe.Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForDifferentDifficulty(BotDifficultyLevel botDifficultyLevel) {
        switch (botDifficultyLevel){
            case EASY:
                return new EasyBotPlayingStrategy();
            case MEDIUM:
                return new MediumBotPlayingStrategy();
        }
        return null;
    }
}
