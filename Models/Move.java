package TicTacToe.Models;

public class Move {
    public Cell getCell() {
        return cell;
    }

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Cell cell;
    private Player player;

}
