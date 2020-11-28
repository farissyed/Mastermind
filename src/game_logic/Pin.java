package game_logic;

import com.google.gson.annotations.Expose;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Pin extends Region {
    private Circle circle;
    @Expose(serialize = true, deserialize = true)
    private PinColor pinColor;

    public Pin(PinColor pinColor) {
        this(pinColor, 0, 0, 25);
    }


    public Pin(PinColor pinColor, double radius) {
        this(pinColor, 0, 0, radius);
    }

    public Pin(PinColor pinColor, int centerX, int centerY , double radius) {
        this.pinColor = pinColor;

        circle = new Circle(centerX, centerY, radius);
        circle.setFill(pinColor.getPinColor());

        getChildren().addAll(circle);
    }

    public void setCircleCenter(double centerX, double centerY) {
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }

    public PinColor getColor() {
        return pinColor;
    }

    public void setPinColor(PinColor pinColor) {
        this.pinColor = pinColor;
        circle.setFill(pinColor.getPinColor());
    }

    @Override
    public String toString() {
        return "Pin{" +
                "pinColor=" + pinColor +
                '}';
    }
}
