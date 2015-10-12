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

    public Game(){
        System.out.println("Select the game mode:");
        System.out.println("(0) Player VS COM (Expert)");
        System.out.println("(1) Player VS RandCOM (Easy)");
        System.out.println("(2) COM VS RandCOM");
        System.out.println("(3) COM VS COM");
        System.out.println("(4) RandCOM VS RandCOM");
        System.out.println("(5) Player VS Player");
        System.out.print("\nYour choice: ");
        int mode = 5;
        try{
            Scanner sc = new Scanner(System.in);
            mode = sc.nextInt();
        } catch (InputMismatchException exception){
            System.out.println("You opted an invalid choice; You will be redirected to PvP");
        }
        executeGame(mode);
    }

    public void executeGame(int mode) {

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
    }

}