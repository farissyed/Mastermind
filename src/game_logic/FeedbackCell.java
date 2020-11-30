package game_logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import user_interface.MastermindApp;

public class FeedbackCell extends Cell {
    private Circle circle;
    private FeedbackPin pin;

    public FeedbackCell(double centerX, double centerY) {
        circle = new Circle(centerX, centerY, 12.5);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        circle.setFill(null);
        this.getChildren().add(circle);
        pin = null;
    }

    public void setFeedbackPin(FeedbackPin pin) {
        this.pin = pin;
        this.getChildren().remove(circle);
        this.getChildren().add(pin);
        pin.setCircleCenter(MastermindApp.PIN_WIDTH / 2.0, MastermindApp.PIN_HEIGHT / 2.0);
    }
}
