package player;

import board.Board;

import java.util.Random;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class RandCOM extends AbstractPlayer {

    public RandCOM(boolean isCross, boolean isFirst) {
        super(isCross, isFirst);
    }

    protected int nextPlay(Board gameBoard) {
        return gameBoard.availablePlays.get(new Random().nextInt(gameBoard.availablePlays.size()));
    }

}
