package game_logic;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;



public class ScorerTest {

    public static boolean compareFeedbackPins(FeedbackPin[] pin1, FeedbackPin[] pin2) {
        FeedbackPin[] p1 = Arrays.copyOf(pin1, pin1.length);
        FeedbackPin[] p2 = Arrays.copyOf(pin2, pin2.length);
        out: for (int i = 0; i < p1.length; i++) {
            for (int j = 0; j < p2.length; j++) {
                if(p2[j] != null && p2[j].getColor().equals(p1[i].getColor())) {
                    p2[j] = null;
                    continue out;
                }
            }
            return false;
        }

        return true;
    }
    public static void main(String[] args){
        org.junit.runner.JUnitCore.main("game_logic.ScorerTest");
    }

    @Test
    public void testFeedback1() {
        CodePin[] userGuess = new CodePin[] {new CodePin(PinColor.Blue), new CodePin(PinColor.Red), new CodePin(PinColor.Green),
        new CodePin(PinColor.Green), new CodePin(PinColor.Orange)};

        CodePin[] actualCode = new CodePin[] {new CodePin(PinColor.Blue), new CodePin(PinColor.Red), new CodePin(PinColor.Green),
                new CodePin(PinColor.Green), new CodePin(PinColor.Orange)};

        FeedbackPin[] feedbackPins = Scorer.feedback(userGuess, actualCode);

        FeedbackPin[] expectedFeedback = new FeedbackPin[] { new FeedbackPin(PinColor.Red), new FeedbackPin(PinColor.Red),
                new FeedbackPin(PinColor.Red), new FeedbackPin(PinColor.Red), new FeedbackPin(PinColor.Red)};

        assertTrue("game_logic.ScorerTest: Guessed correct code test fail", compareFeedbackPins(expectedFeedback, feedbackPins));
    }
}
