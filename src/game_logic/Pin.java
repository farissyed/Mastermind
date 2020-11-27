package game_logic;

import com.google.gson.annotations.Expose;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static user_interface.MastermindApp.TOTAL_SIZE;

public class Pin extends StackPane {
    @Expose(serialize = true, deserialize = true)
    private PinColor pinColor;

    public Pin(PinColor pinColor) {
        this.pinColor = pinColor;
    }

    public Pin(PinColor c, int x, int y, int cellSize) {
        this.pinColor = pinColor;

        double scale = 0.35;

        relocate(x, y);

        Circle circle = new Circle(cellSize * scale);
        circle.setFill(c.pinColor);

        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(cellSize *0.025);

        circle.setTranslateX((cellSize - cellSize * scale * 2) / 2);
        circle.setTranslateY((cellSize - cellSize * scale * 2) / 2);

        getChildren().addAll(circle);
    }

    public PinColor getColor() {
        return pinColor;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "pinColor=" + pinColor +
                '}';
    }
}
