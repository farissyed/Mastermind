package game_logic;

import com.google.gson.annotations.Expose;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Pin class which stores information such as the color of the pin and the radius used within GUI elements.
 */
public class Pin extends Region {
    protected Circle circle;
    @Expose(serialize = true, deserialize = true)
    private PinColor pinColor;

    /**
     * Constructor for Pin Class
     * @param pinColor Color of pin
     * @param radius Radius to be used in GUI elements
     */
    public Pin(PinColor pinColor, double radius) {
        this(pinColor, 0, 0, radius);
    }

    /**
     * Constructor for Pin Class
     * @param pinColor Color of pin
     * @param centerX x-coordinate of the center circle
     * @param centerY y-coordinate of the center circle
     * @param radius Radius to be used in GUI elements
     */
    public Pin(PinColor pinColor, int centerX, int centerY, double radius) {
        this.pinColor = pinColor;

        circle = new Circle(centerX, centerY, radius);
        circle.setFill(pinColor.getPinColor());

        getChildren().addAll(circle);
    }

    /**
     * Sets the center of the circle
     * @param centerX x-coordinate of the center circle
     * @param centerY y-coordinate of the center circle
     */
    public void setCircleCenter(double centerX, double centerY) {
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }

    /**
     * @return x-coordinate of the center circle
     */
    public double getCenterX() {
        return circle.getCenterX();
    }

    /**
     * @return y-coordinate of the center circle
     */
    public double getCenterY() {
        return circle.getCenterY();
    }

    /**
     * @return Color of the pin
     */
    public PinColor getColor() {
        return pinColor;
    }

    /**
     * Sets the pin color
     * @param pinColor Color of the pin
     */
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
