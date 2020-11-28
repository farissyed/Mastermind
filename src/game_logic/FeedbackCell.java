package game_logic;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FeedbackCell extends Cell {
    private Circle circle;
    private FeedbackPin pin;

    public FeedbackCell(double centerX, double centerY) {
        circle = new Circle(centerX, centerY, 25);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        circle.setFill(null);
        this.getChildren().add(circle);
        pin = null;
    }

    public void setFeedbackPin(FeedbackPin pin) {
        this.pin = pin;
        circle = null;
    }
}
