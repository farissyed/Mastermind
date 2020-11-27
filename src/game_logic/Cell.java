package game_logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private Pin pin;
    private int cellX;
    private int y;

    public boolean hasPin() {
        return pin != null;
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return y;
    }

    public Cell(Color c, int cellX, int y, int cellSize) {
        setWidth(cellSize);
        setHeight(cellSize);

        this.cellX = cellX;
        this.y = y;

        relocate(cellX * cellSize, y * cellSize);

        setFill(c);

    }
}
