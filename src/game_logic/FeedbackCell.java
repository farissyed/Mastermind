package game_logic;

import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

public class FeedbackCell extends Region {
    private Circle circle;
    private FeedbackPin pin;

    public FeedbackCell() {
        circle = new Circle(50);
        this.getChildren().add(circle);
        pin = null;
    }

    public void setFeedbackPin(FeedbackPin pin) {
        this.pin = pin;
        circle = null;
    }
}
