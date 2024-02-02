package TicTacToe.Models;

import java.util.Collection;
import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType type;
    private int id;
    private Scanner scanner;

    public Player(char symbol, String name, PlayerType type, int id) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.id = id;
        this.scanner = new Scanner(System.in);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cell makeMove(Board board) throws InterruptedException {
        System.out.println(this.name + ", it's your turn. Please enter the positon-");

        int row = scanner.nextInt()-1;
        int col = scanner.nextInt()-1;

        while(!validateMove(row, col, board)){
            System.out.println(name + ", it's a wrong position !! Please enter again");
            row = scanner.nextInt()-1;
            col = scanner.nextInt()-1;
        }

        Cell cell = board.getBoard().get(row).get(col);
        cell.setState(CellState.FILL);
        cell.setPlayer(this);

        return cell;
    }

    private boolean validateMove(int row, int col, Board board) {
        if(row<0 || row >= board.getDimension()){
            return false;
        }
        if(col<0 || col>=board.getDimension()){
            return false;
        }
        if(CellState.FILL.equals(board.getBoard().get(row).get(col).getState())){
            return false;
        }
        return true;
    }
}
