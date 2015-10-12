package game;

import board.Board;
import player.COM;
import player.Person;
import player.AbstractPlayer;
import player.RandCOM;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class Game {

    protected Board ticTacToe;
    private boolean coinToss;
    private boolean hasCross;
    private boolean oddTurn;
    private int mode;

    private static final String MODE_0 = "(0) Player VS COM (Expert)";
    private static final String MODE_1 = "(1) Player VS RandCOM (Easy)";
    private static final String MODE_2 = "(2) COM VS RandCOM";
    private static final String MODE_3 = "(3) COM VS COM";
    private static final String MODE_4 = "(4) RandCOM VS RandCOM";
    private static final String MODE_5 = "(5) Player VS Player";

    public Game() {
        System.out.println("Select the game mode (mod 6):");
        System.out.println(MODE_0);
        System.out.println(MODE_1);
        System.out.println(MODE_2);
        System.out.println(MODE_3);
        System.out.println(MODE_4);
        System.out.println(MODE_5);
        System.out.print("\nYour choice: ");
        mode = 5;
        try {
            Scanner sc = new Scanner(System.in);
            mode = sc.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("You opted an invalid choice; You will be redirected to PvP");
        }
        System.out.println("You chose mode " + numberToMode(mode));
    }

    private String numberToMode(int mode) {
        String modeName = "";
        switch (mode % 6) {
            case 0:
                modeName = MODE_0;
                break;
            case 1:
                modeName = MODE_1;
                break;
            case 2:
                modeName = MODE_2;
                break;
            case 3:
                modeName = MODE_3;
                break;
            case 4:
                modeName = MODE_4;
                break;
            case 5:
                modeName = MODE_5;
                break;
        }
        return modeName;
    }

    public void run() {

        ticTacToe = new Board();
        oddTurn = true;
        toss();

        switch (mode % 6) {
            case 0:
                startGame(new Person(hasCross, coinToss), new COM(!hasCross, !coinToss));
                break;
            case 1:
                startGame(new Person(hasCross, coinToss), new RandCOM(!hasCross, !coinToss));
                break;
            case 2:
                startGame(new COM(hasCross, coinToss), new RandCOM(!hasCross, !coinToss));
                break;
            case 3:
                startGame(new COM(hasCross, coinToss), new COM(!hasCross, !coinToss));
                break;
            case 4:
                startGame(new RandCOM(hasCross, coinToss), new RandCOM(!hasCross, !coinToss));
                break;
            case 5:
                startGame(new Person(hasCross, coinToss), new Person(!hasCross, !coinToss));
                break;
        }
    }

    private void startGame(AbstractPlayer p1, AbstractPlayer p2) {
        while (!ticTacToe.win() || !ticTacToe.tie()) {
            if (coinToss == oddTurn)
                computePlay(p1);
            else
                computePlay(p2);
            oddTurn = !oddTurn;
        }
    }


    private void computePlay(AbstractPlayer p) {
        p.makeMove(ticTacToe, p.nextPlay(ticTacToe));
        ticTacToe.printBoard();
    }

    private void toss() {
        Random rand = new Random();
        coinToss = rand.nextBoolean();
        hasCross = rand.nextBoolean();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}