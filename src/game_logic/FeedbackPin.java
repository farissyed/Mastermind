package game_logic;

import javafx.scene.paint.Color;

/**
 * Specific class for feedback pin
 */
public class FeedbackPin extends Pin {

    /**
     *
     * @param c Color of the pin
     */
    public FeedbackPin(PinColor c) {
        super(c, 12.5);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
    }


}
