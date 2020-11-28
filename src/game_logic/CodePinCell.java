package game_logic;

import com.sun.org.apache.bcel.internal.classfile.Code;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CodePinCell extends Cell {
    private Circle circle;
    private CodePin pin;

    public CodePinCell(double centerX, double centerY) {
        circle = new Circle(centerX, centerY, 30);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        circle.setFill(null);
        this.getChildren().add(circle);
        pin = null;
    }

    public void setFeedbackPin(CodePin pin) {
        this.pin = pin;
        this.getChildren().remove(circle);
        this.getChildren().add(pin);
    }
}
