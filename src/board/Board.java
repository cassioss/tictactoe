package board;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class Board {

    private String[][] board;
    private List<Integer> availablePlays;
    private int plays;

    public int getPlays() {
        return plays;
    }

    public List<Integer> getAvailablePlays() {
        return availablePlays;
    }

    public Board() {
        clear();
    }

    public boolean canPlayAt(int play) {
        return availablePlays.contains(play);
    }

    public void playAt(int play, String symbol) {
        availablePlays.remove(Integer.valueOf(play));
        board[play / 3][play % 3] = symbol;
        plays++;
    }

    public void printBoard() {
        System.out.println();
        boolean first = true;
        for (int y = 0; y < 3; y++) {
            if (first)
                first = false;
            else
                System.out.println("-----------");
            boolean start = true;
            for (int x = 0; x < 3; x++) {
                if (start)
                    start = false;
                else
                    System.out.print("|");
                if (board[y][x] == null)
                    System.out.print("   ");
                else
                    System.out.print(" " + board[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Available plays: " + availablePlays.toString() + "\n");
    }

    private boolean winHorizontalAt(int posY) {
        return (board[posY][0] != null && board[posY][1] != null && board[posY][2] != null) &&
                board[posY][0].equals(board[posY][1]) && board[posY][1].equals(board[posY][2]);
    }

    private boolean winHorizontalAt(int posY, String symbol) {
        return winHorizontalAt(posY) && board[posY][1].equals(symbol);
    }

    private boolean winVerticalAt(int posX) {
        return (board[0][posX] != null && board[1][posX] != null && board[2][posX] != null) &&
                board[0][posX].equals(board[1][posX]) && board[1][posX].equals(board[2][posX]);
    }

    private boolean winVerticalAt(int posX, String symbol) {
        return winVerticalAt(posX) && board[1][posX].equals(symbol);
    }

    private boolean winAtMainDiagonal() {
        return (board[0][0] != null && board[1][1] != null && board[2][2] != null) &&
                board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]);
    }

    private boolean winAtSecDiagonal() {
        return (board[0][2] != null && board[1][1] != null && board[2][0] != null) &&
                board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]);
    }

    private boolean winHorizontal() {
        return winHorizontalAt(0) || winHorizontalAt(1) || winHorizontalAt(2);
    }

    private boolean winHorizontal(String symbol) {
        return winHorizontalAt(0, symbol) || winHorizontalAt(1, symbol) || winHorizontalAt(2, symbol);
    }

    private boolean winVertical() {
        return winVerticalAt(0) || winVerticalAt(1) || winVerticalAt(2);
    }

    private boolean winVertical(String symbol) {
        return winVerticalAt(0, symbol) || winVerticalAt(1, symbol) || winVerticalAt(2, symbol);
    }

    private boolean winDiagonal() {
        return winAtMainDiagonal() || winAtSecDiagonal();
    }

    private boolean winDiagonal(String symbol) {
        return winDiagonal() && board[1][1].equals(symbol);
    }

    public boolean win() {
        return winHorizontal() || winVertical() || winDiagonal();
    }

    public boolean win(String symbol) {
        return winHorizontal(symbol) || winVertical(symbol) || winDiagonal(symbol);
    }

    public boolean tie() {
        return availablePlays.size() == 0 && !win();
    }

    private void clear() {
        board = new String[3][3];
        availablePlays = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            availablePlays.add(i);
        plays = 0;
    }

    public void requestClear() {
        if (win() || tie())
            clear();
    }

}
