package player;

import board.Board;

import java.util.List;
import java.util.Random;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class COM extends AbstractPlayer {

    public COM(boolean isCross, boolean isFirst) {
        super(isCross, isFirst);
    }

    protected int nextPlay(Board gameBoard) {
        if (isFirst)
            return playFirst(gameBoard);
        else
            return playSecond(gameBoard);
    }

    private int playFirst(Board gameBoard) {
        int play = 0;
        switch (gameBoard.plays) {
            case 0:
                play = playAtCorners();
                break;
            case 2:
                break;
            case 4:
                break;
            case 6:
                break;
            case 8:
                play = randomPlay(gameBoard.availablePlays);    // There will be only one possibility
                break;
            default:
                play = 0;
                break;
        }
        return play;
    }

    private int playSecond(Board gameBoard) {
        int play = 0;
        switch (gameBoard.plays) {
            case 1:
                play = tryAtCenter(gameBoard);
                break;
            case 3:
                break;
            case 5:
                break;
            case 7:
                break;
            default:
                play = 0;
                break;
        }
        return play;
    }

    private int tryAtCenter(Board gameBoard) {
        if (gameBoard.canPlayAt(Board.CENTER))
            return memorizePlay(Board.CENTER);
        else
            return playAtCorners();
    }

    private int playAtCorners() {
        return randomPlay(new int[]{0, 2, 6, 8});   // Corners
    }

    private int randomPlay(int[] plays) {
        return memorizePlay(plays[new Random().nextInt(plays.length)]);
    }

    private int randomPlay(List<Integer> plays) {
        return memorizePlay(plays.get(new Random().nextInt(plays.size())));
    }

    private int memorizePlay(int play) {
        alreadyPlayed.add(play);
        return play;
    }

}
