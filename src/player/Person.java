package player;

import board.Board;

import java.util.Scanner;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class Person extends AbstractPlayer {

    public Person(boolean isCross, boolean isFirst) {
        super(isCross, isFirst);
    }

    public int nextPlay(Board gameBoard) {
        System.out.print("Make your move: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
