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

    public int nextPlay(Board gameBoard) {
        return gameBoard.getAvailablePlays().get(new Random().nextInt(gameBoard.getAvailablePlays().size()));
    }

}
