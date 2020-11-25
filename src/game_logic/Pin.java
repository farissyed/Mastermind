package game_logic;

import com.google.gson.annotations.Expose;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static user_interface.MastermindApp.CELL_SIZE;

public class Pin extends StackPane {
    @Expose(serialize = true, deserialize = true)
    private PinColor pinColor;

    public Pin(PinColor pinColor) {
        this.pinColor = pinColor;
    }

    public Pin(PinColor c, int x, int y) {
        this.pinColor = pinColor;

        double scale = 0.35;

        relocate(x*CELL_SIZE, y*CELL_SIZE);

        Circle circle = new Circle(CELL_SIZE * scale);
        circle.setFill(c.pinColor);

        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(CELL_SIZE*0.025);

        circle.setTranslateX((CELL_SIZE - CELL_SIZE * scale * 2) / 2);
        circle.setTranslateY((CELL_SIZE - CELL_SIZE * scale * 2) / 2);

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
