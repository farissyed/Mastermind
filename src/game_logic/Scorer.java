package game_logic;

import user_interface.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Scorer {
    public static FeedbackPin[] feedback(CodePin[] uGuess, CodePin[] aCode) {
        CodePin[] userGuess = Arrays.copyOf(uGuess, uGuess.length);
        CodePin[] actualCode = Arrays.copyOf(aCode, aCode.length);
        ArrayList<FeedbackPin> feedbackPins = new ArrayList<>();
        if(userGuess.length == actualCode.length) {
            for (int i = 0; i < actualCode.length; i++) {
                Color c = actualCode[i].getColor();
                int colorIndex = indexOfColor(c, userGuess);
                if(colorIndex == i) {
                    feedbackPins.add(new FeedbackPin(Color.Red));
                    userGuess[i] = null;
                }
                else if(colorIndex != -1) {
                    feedbackPins.add(new FeedbackPin(Color.White));
                    userGuess[i] = null;
                }
            }
        }

        FeedbackPin[] feedbackPins1 = new FeedbackPin[feedbackPins.size()];
        feedbackPins.toArray(feedbackPins1);
        return feedbackPins1;
    }

    /**
     * Get index of where the color is in the array of pins
     * @param color
     * @param pins
     * @return index of color in pins or -1 if not found
     */
    private static int indexOfColor(Color color, CodePin[] pins) {
        for (int i = 0; i < pins.length; i++) {
            if(pins[i] != null && pins[i].getColor().equals(color)) {
                return i;
            }
        }
        return -1;
    }

    public static CodePin[] generateRandomCode(boolean duplicatesAllowed) {
        CodePin[] code = new CodePin[5];
        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));


        for (int i = 0; i < code.length; i++) {
            Random random = new Random();
            Color c = colors.get(random.nextInt(colors.size()));
            if(!duplicatesAllowed) {
                colors.remove(c);
            }
            code[i] = new CodePin(c);
        }


        return code;
    }

    /**
     * This method should be called when the player makes a guess.  It should create the CodePin array, get the feedback, and send that feedback back to
     * the UI to be displayed on the screen
     */
    public static void guess(CodePin[] userGuess) {
        FeedbackPin[] feedbackPins = feedback(userGuess, Main.getCode());
    }
}
