package player;

import java.util.Random;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class Game {

    protected Board ticTacToe;
    private boolean coinToss;
    private boolean hasCross;
    private boolean oddTurn;

    public Game(int mode) {

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

    private void startGame(Player p1, Player p2) {
        while (!ticTacToe.win() || !ticTacToe.tie()) {
            if (coinToss == oddTurn)
                computePlay(p1);
            else
                computePlay(p2);
            oddTurn = !oddTurn;
        }
    }


    private void computePlay(Player p) {
        p.makeMove(ticTacToe, p.nextPlay(ticTacToe));
        ticTacToe.printBoard();
    }

    private void toss() {
        Random rand = new Random();
        coinToss = rand.nextBoolean();
        hasCross = rand.nextBoolean();
    }

    public static void main(String[] args) {
        Game game = new Game(5);
    }

}