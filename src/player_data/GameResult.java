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

    public GameResult(CodePin[] finalCode, int numberOfTurns, boolean duplicatesAllowed) {
        this.finalCode = finalCode;
        this.numberOfTurns = numberOfTurns;
        this.duplicatesAllowed = duplicatesAllowed;
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
