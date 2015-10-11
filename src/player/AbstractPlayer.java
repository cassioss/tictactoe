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
    protected int score;
    protected boolean isFirst;
    protected List<Integer> alreadyPlayed;

    public AbstractPlayer(boolean isCross, boolean isFirst) {
        this.isCross = isCross;
        this.isFirst = isFirst;
        score = 0;
        alreadyPlayed = new ArrayList<>();
    }

    protected String symbol() {
        if (isCross)
            return "X";
        else
            return "O";
    }

    protected void makeMove(Board gameBoard, int play) {
        if (!gameBoard.availablePlays.contains(play)) {
            gameBoard.playAt(play, symbol());
            checkWinsAt(gameBoard);
        }
    }

    protected void checkWinsAt(Board gameBoard) {
        if (gameBoard.win()) {
            score++;
            gameBoard.clear();
            alreadyPlayed = new ArrayList<>();
        }
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

    protected abstract int nextPlay(Board gameBoard);
}
