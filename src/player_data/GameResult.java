package player_data;

import com.google.gson.annotations.Expose;
import game_logic.CodePin;

import java.util.Arrays;

/**
 * Stores data for each game that the player plays
 */
public class GameResult {
    @Expose(serialize = true, deserialize = true)
    private final CodePin[] finalCode;
    @Expose(serialize = true, deserialize = true)
    private final int numberOfTurns;
    @Expose(serialize = true, deserialize = true)
    private final boolean duplicatesAllowed;
    @Expose(serialize = true, deserialize = true)
    private final boolean gameWon;


    /**
     * Construct a GameResult object with the given object
     * @param finalCode The code for the game
     * @param numberOfTurns The number of turns it took the player to get the code
     * @param duplicatesAllowed true if duplicates were allowed, false otherwise
     * @param gameWon true if the player won the game
     */
    public GameResult(CodePin[] finalCode, int numberOfTurns, boolean duplicatesAllowed, boolean gameWon) {
        this.finalCode = finalCode;
        this.numberOfTurns = numberOfTurns;
        this.duplicatesAllowed = duplicatesAllowed;
        this.gameWon = gameWon;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public CodePin[] getFinalCode() {
        return finalCode;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public boolean isDuplicatesAllowed() {
        return duplicatesAllowed;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "finalCode=" + Arrays.toString(finalCode) +
                ", numberOfTurns=" + numberOfTurns +
                ", duplicatesAllowed=" + duplicatesAllowed +
                '}';
    }
}
