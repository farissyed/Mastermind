package game_logic;

import user_interface.MastermindApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Scorer {

    /**
     * Produce an array of feedback pins based on the user's guess and the correct code
     *
     * @param uGuess User guess of the code
     * @param aCode Actual code set by codemaster
     * @return
     */
    public static FeedbackPin[] feedback(CodePin[] uGuess, CodePin[] aCode) {
        CodePin[] userGuess = Arrays.copyOf(uGuess, uGuess.length);
        CodePin[] actualCode = Arrays.copyOf(aCode, aCode.length);
        ArrayList<FeedbackPin> feedbackPins = new ArrayList<>();
        if (userGuess.length == actualCode.length) {
            for (int i = 0; i < actualCode.length; i++) {
                PinColor c = actualCode[i].getColor();
                if (userGuess[i] != null && userGuess[i].getColor().equals(c)) {
                    feedbackPins.add(new FeedbackPin(PinColor.CORRECT_POSITION_COLOR));
                    actualCode[i] = null;
                    userGuess[i] = null;
                }
            }
            for (int i = 0; i < actualCode.length; i++) {
                if (actualCode[i] != null) {
                    PinColor c = actualCode[i].getColor();

                    int colorIndex = indexOfColor(c, userGuess);
                    if (colorIndex != -1) {
                        feedbackPins.add(new FeedbackPin(PinColor.CORRECT_COLOR_COLOR));
                        userGuess[colorIndex] = null;
                    }
                }
            }
        }

        FeedbackPin[] feedbackPins1 = new FeedbackPin[feedbackPins.size()];
        feedbackPins.toArray(feedbackPins1);
        return feedbackPins1;
    }

    /**
     * Get index of where the color is in the array of pins
     *
     * @param pinColor The color to find
     * @param pins     The array to find the color within
     * @return index of color in pins or -1 if not found
     */
    private static int indexOfColor(PinColor pinColor, CodePin[] pins) {
        for (int i = 0; i < pins.length; i++) {
            if (pins[i] != null && pins[i].getColor().equals(pinColor)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Generate a random code for the game
     *
     * @param duplicatesAllowed true if duplicates should be allowed in the code
     * @return a random code for the game
     */
    public static CodePin[] generateRandomCode(boolean duplicatesAllowed) {
        CodePin[] code = new CodePin[5];
        ArrayList<PinColor> pinColors = (ArrayList<PinColor>) PinColor.GUESS.clone();


        for (int i = 0; i < code.length; i++) {
            Random random = new Random();
            PinColor c = pinColors.get(random.nextInt(pinColors.size()));
            if (!duplicatesAllowed) {
                pinColors.remove(c);
            }
            code[i] = new CodePin(c);
        }


        return code;
    }

    /**
     * Determine whether the code is correct based on the feedback that was produced
     *
     * @param feedbackPins Array of feedback pins based on the code
     * @return true if code is correct
     */
    public static boolean codeIsCorrect(FeedbackPin[] feedbackPins) {
        if (feedbackPins.length != 5) {
            return false;
        }
        for (int i = 0; i < feedbackPins.length; i++) {
            if (!(feedbackPins[i].getColor().equals(PinColor.CORRECT_POSITION_COLOR))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method should be called when the player makes a guess.  It should create the CodePin array, get the feedback, and send that feedback back to the UI to be displayed on the screen
     * @param userGuess
     * @return Feedback for user to be displayed by UI
     */
    public static FeedbackPin[] guess(CodePin[] userGuess) {
        return feedback(userGuess, MastermindApp.getCode());
    }
}