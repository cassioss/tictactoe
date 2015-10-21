package player;

import board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public abstract class AbstractPlayer {

    protected boolean isCross;
    public int score;
    protected boolean isFirst;
    protected List<Integer> iAlreadyPlayed;
    protected List<Integer> opponentPlayed;

    public AbstractPlayer(boolean isCross, boolean isFirst) {
        this.isCross = isCross;
        this.isFirst = isFirst;
        score = 0;
        iAlreadyPlayed = new ArrayList<>();
        opponentPlayed = new ArrayList<>();
    }

    protected String symbol() {
        if (isCross)
            return "X";
        else
            return "O";
    }

    public void makeMove(Board gameBoard, int play) {
        if (gameBoard.canPlayAt(play)) {
            gameBoard.playAt(play, symbol());
            iAlreadyPlayed.add(play);
            checkWinsAt(gameBoard);
        }
    }

    protected void checkWinsAt(Board gameBoard) {
        if (gameBoard.win(symbol())) {
            score++;
        }
    }

    public void newGame() {
        iAlreadyPlayed = new ArrayList<>();
        opponentPlayed = new ArrayList<>();
    }

    protected boolean playedAtCorner(int lastPlay) {
        return lastPlay == 0 || lastPlay == 2 || lastPlay == 6 || lastPlay == 8;
    }

    protected boolean playedAtEdge(int lastPlay) {
        return lastPlay == 1 || lastPlay == 3 || lastPlay == 5 || lastPlay == 7;
    }

    protected boolean playedAtCenter(int lastPlay) {
        return lastPlay == 4;
    }

    public abstract int nextPlay(Board gameBoard);

    public void keepOpponentMove(int play) {
        opponentPlayed.add(play);
    }

    public String playerDetails(boolean fullDetail) {
        String player = "Symbol: " + symbol() + "\n";
        if (fullDetail) {
            player += "Played: " + iAlreadyPlayed.toString() + "\n";
            player += "Opponent played: " + opponentPlayed.toString() + "\n";
        }
        player += "Score: " + score + "\n";
        return player;
    }
}