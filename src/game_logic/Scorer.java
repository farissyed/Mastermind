package game_logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Scorer {
    public static FeedbackPin[] feedback(CodePin[] userGuess, CodePin[] actualCode) {
        ArrayList<FeedbackPin> feedbackPins = new ArrayList<>();
        if(userGuess.length == actualCode.length) {
            for (int i = 0; i < actualCode.length; i++) {
                Color c = actualCode[i].getColor();
                int colorIndex = indexOfColor(c, userGuess);
                if(colorIndex == i) {
                    feedbackPins.add(new FeedbackPin(Color.Red));
                }
                else if(colorIndex != -1) {
                    feedbackPins.add(new FeedbackPin(Color.White));
                }
            }
        }

        return feedbackPins.toArray();
    }

    /**
     * Get index of where the color is in the array of pins
     * @param color
     * @param pins
     * @return index of color in pins or -1 if not found
     */
    private static int indexOfColor(Color color, CodePin[] pins) {
        for (int i = 0; i < pins.length; i++) {
            if(pins[i].getColor().equals(color)) {
                return i;
            }
        }
        return -1;
    }
}
