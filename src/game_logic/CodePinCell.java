package game_logic;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import user_interface.MastermindApp;

public class CodePinCell extends Cell {
    private final Circle circle;
    private CodePin pin;
    private int currentColorIndex;

    public CodePinCell(double centerX, double centerY) {
        circle = new Circle(centerX, centerY, 30);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        circle.setFill(null);
        this.getChildren().add(circle);
        pin = null;

        this.setStyle("-fx-cursor: hand;");

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(pin == null) {
                    currentColorIndex = 0;
                    setCodePin(new CodePin(PinColor.GUESS.get(currentColorIndex)));
                    pin.setCircleCenter(MastermindApp.CODE_PIN_WIDTH / 2.0, MastermindApp.CODE_PIN_HEIGHT / 2.0);
                }
                else {
                    currentColorIndex++;
                    if(currentColorIndex >= PinColor.GUESS.size()) {
                        currentColorIndex = 0;
                    }
                    pin.setPinColor(PinColor.GUESS.get(currentColorIndex));
                }
            }
        });
    }

    public CodePin getPin() {
        return pin;
    }

    public void setCodePin(CodePin pin) {
        this.pin = pin;
        this.getChildren().remove(circle);
        this.getChildren().add(pin);
    }
}
