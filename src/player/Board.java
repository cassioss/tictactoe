package player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class Board {

    protected static final int CENTER = 4;

    protected String[][] board;
    protected List<Integer> availablePlays;
    protected int plays;
    protected Integer lastPlay;

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
    }

    private boolean winHorizontalAt(int posY) {
        return (board[posY][0] != null && board[posY][1] != null && board[posY][2] != null) &&
                board[posY][0].equals(board[posY][1]) && board[posY][1].equals(board[posY][2]);
    }

    private boolean winVerticalAt(int posX) {
        return (board[0][posX] != null && board[1][posX] != null && board[2][posX] != null) &&
                board[0][posX].equals(board[1][posX]) && board[1][posX].equals(board[2][posX]);
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

    private boolean winVertical() {
        return winVerticalAt(0) || winVerticalAt(1) || winVerticalAt(2);
    }

    private boolean winDiagonal() {
        return winAtMainDiagonal() || winAtSecDiagonal();
    }

    protected boolean win() {
        return winHorizontal() || winVertical() || winDiagonal();
    }

    protected boolean tie() {
        return availablePlays.size() == 0 && !win();
    }

    protected void clear() {
        board = new String[3][3];
        availablePlays = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            availablePlays.add(i);
        plays = 0;
        lastPlay = null;
    }

}
