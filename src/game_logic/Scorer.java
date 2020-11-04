package game_logic;

import user_interface.MastermindApp;

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
                PinColor c = actualCode[i].getColor();
                int colorIndex = indexOfColor(c, userGuess);
                if(colorIndex == i) {
                    feedbackPins.add(new FeedbackPin(PinColor.Red));
                    userGuess[i] = null;
                }
                else if(colorIndex != -1) {
                    feedbackPins.add(new FeedbackPin(PinColor.White));
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
     * @param pinColor
     * @param pins
     * @return index of color in pins or -1 if not found
     */
    private static int indexOfColor(PinColor pinColor, CodePin[] pins) {
        for (int i = 0; i < pins.length; i++) {
            if(pins[i] != null && pins[i].getColor().equals(pinColor)) {
                return i;
            }
        }
        return -1;
    }

    public static CodePin[] generateRandomCode(boolean duplicatesAllowed) {
        CodePin[] code = new CodePin[5];
        ArrayList<PinColor> pinColors = new ArrayList<>(Arrays.asList(PinColor.values()));


        for (int i = 0; i < code.length; i++) {
            Random random = new Random();
            PinColor c = pinColors.get(random.nextInt(pinColors.size()));
            if(!duplicatesAllowed) {
                pinColors.remove(c);
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
        FeedbackPin[] feedbackPins = feedback(userGuess, MastermindApp.getCode());
    }
}
