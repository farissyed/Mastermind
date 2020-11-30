package game_logic;

import javafx.scene.paint.Color;

public class FeedbackPin extends Pin {
    public FeedbackPin(PinColor c) {
        super(c, 12.5);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
    }


}
