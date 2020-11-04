package game_logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import user_interface.MastermindApp;

public class Cell extends Rectangle {

    private Pin pin;

    public boolean hasPin() {
        return pin != null;
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public Cell(Color c, int x, int y) {
        setWidth(MastermindApp.CELL_SIZE);
        setHeight(MastermindApp.CELL_SIZE);

        relocate(x * MastermindApp.CELL_SIZE, y * MastermindApp.CELL_SIZE);

        setFill(c);

    }
}
