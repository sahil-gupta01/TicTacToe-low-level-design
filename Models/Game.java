package TicTacToe.Models;

import TicTacToe.exception.InvalidPlayersOrBoardSizeException;
import TicTacToe.exception.MoreThanOneBotException;
import TicTacToe.exception.NotUniqueSymbolsException;
import TicTacToe.winningStrategies.WinningStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private GameState gameState;
    private int nextPlayerInd;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    private List<WinningStrategies> winningStrategies;
    private Player winner;

    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    //Constructor for the game
    private Game(int dimension, List<Player> players, List<WinningStrategies> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROG;
        this.nextPlayerInd = 0;
        this.moves = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerInd() {
        return nextPlayerInd;
    }

    public void setNextPlayerInd(int nextPlayerInd) {
        this.nextPlayerInd = nextPlayerInd;
    }

    public static Builder createBuilder() throws MoreThanOneBotException {
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() throws InterruptedException {
        Player currPlayer = players.get(nextPlayerInd);
        Cell cell = currPlayer.makeMove(board);
        Move move = new Move(cell, currPlayer);
        moves.add(move);

        if(checkWinner(move, board)){
            gameState = GameState.SUCCESS;
            winner = currPlayer;
            return;
        }
        if(moves.size() == board.getDimension()*board.getDimension()){
            gameState = GameState.DRAW;
            return;
        }

        nextPlayerInd++;
        nextPlayerInd = nextPlayerInd % players.size();
    }

    private boolean checkWinner(Move move, Board board) {
        for(WinningStrategies winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board,move)) {
                return true;
            }
        }
        return false;
    }

    public void performUndo() {
        if(moves.size() == 0){
            System.out.println("You cannot undo the move as no move has been taken!!");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(moves.size()-1);
        Cell currCell = lastMove.getCell();
        currCell.setState(CellState.EMPTY);
        currCell.setPlayer(null);

        for(WinningStrategies winningStrategy:winningStrategies){
            winningStrategy.performUndo(board,lastMove);
        }
        if (nextPlayerInd != 0) {
            nextPlayerInd--;
        }
        else{
            nextPlayerInd = players.size() - 1;
        }
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;
        private Builder(){
            this.dimension = 0;
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
        public Game build() throws MoreThanOneBotException, NotUniqueSymbolsException, InvalidPlayersOrBoardSizeException {
            //validating factors
            // 1. bot count <=1
            // 2. validate unique symbol
            // 3. validate dimensions and player count

            validateBotCount();
            validateUniqueSymbol();
            validateDimensionsAndPlayerCount();

            return new Game(dimension, players, winningStrategies);
        }

        private void validateDimensionsAndPlayerCount() throws InvalidPlayersOrBoardSizeException {
            if(players.size()!=(dimension-1)){
                throw new InvalidPlayersOrBoardSizeException();
            }
        }

        private void validateUniqueSymbol() throws NotUniqueSymbolsException {
            Set<Character> uniqueSymbols = new HashSet<>();

            for(Player player:players){
                if(uniqueSymbols.contains(player.getSymbol())){
                    throw new NotUniqueSymbolsException();
                }
                else{
                    uniqueSymbols.add(player.getSymbol());
                }
            }


        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount = 0;

            for(Player player:players){
                if(player.getType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1)
                throw new MoreThanOneBotException();
        }

    }
}
