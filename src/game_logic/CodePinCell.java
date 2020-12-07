package game_logic;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import user_interface.MastermindApp;

import static user_interface.MastermindApp.*;

/**
 * Class to represent CodePinCells that hold CodePins
 */
public class CodePinCell extends Cell {
    private final Circle circle;
    private CodePin pin;
    private int currentColorIndex;
    private boolean isDisabled;

    /**
     * Construct a CodePinCell with the specified center values
     * @param centerX The center of the CodePinCell x coordinate within its container
     * @param centerY The center of the CodePinCell y coordinate within its container
     * @param isDisabled true if the CodePinCell should not be clickable and cycle through colors
     */
    public CodePinCell(double centerX, double centerY, boolean isDisabled) {
        circle = new Circle(centerX, centerY, 20);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        circle.setFill(null);
        this.getChildren().add(circle);
        pin = null;
        this.isDisabled = isDisabled;
        if(!isDisabled) {
            this.setStyle("-fx-cursor: hand;");

            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (pin == null) {
                        currentColorIndex = 0;
                        setCodePin(new CodePin(PinColor.GUESS.get(currentColorIndex)));
                        pin.setCircleCenter(MastermindApp.PIN_WIDTH / 2.0, MastermindApp.PIN_HEIGHT / 2.0);
                    }
                    else {
                        currentColorIndex++;
                        if (currentColorIndex >= PinColor.GUESS.size()) {
                            currentColorIndex = 0;
                        }
                        pin.setPinColor(PinColor.GUESS.get(currentColorIndex));
                    }
                    if(allUserGuessPinsFilled()) {
                        makeGuessButton.setDisable(false);
                    }
                    else {
                        makeGuessButton.setDisable(true);
                    }
                }
            });
        }
    }

    /**
     * Check if the user has filled in their entire guess yet
     * @return true if the user has filled in their entire guess yet
     */
    public boolean allUserGuessPinsFilled() {
        for (int i = 0; i < 5; i++) {
            CodePinCell temp = ((CodePinCell)(getCellAt(0, i, userGuessGrid)));
            if(temp.getPin() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the current pin
     * @return
     */
    public CodePin getPin() {
        return pin;
    }

    /**
     * Remove the CodePin from this cell
     */
    public void clearPin() {
        this.getChildren().remove(pin);
        this.pin = null;
        this.getChildren().add(circle);
    }

    /**
     * Set the CodePin for the Cell
     * @param pin The CodePin that this cell will hold
     */
    public void setCodePin(CodePin pin) {
        this.pin = pin;
        this.getChildren().remove(circle);
        this.getChildren().add(pin);
    }
}
