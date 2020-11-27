package game_logic;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static user_interface.MastermindApp.CELL_SIZE;

public class Pin extends StackPane {
    private PinColor pinColor;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Pin(PinColor pinColor) {
        this.pinColor = pinColor;
    }

    public Pin(PinColor c, int x, int y) {
        this.pinColor = pinColor;

        move(x*CELL_SIZE, y*CELL_SIZE);

        double scale = 0.35;

        relocate(x*CELL_SIZE, y*CELL_SIZE);

        Circle circle = new Circle(CELL_SIZE * scale);
        circle.setFill(c.color);

        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(CELL_SIZE*0.025);

        circle.setTranslateX((CELL_SIZE - CELL_SIZE * scale * 2) / 2);
        circle.setTranslateY((CELL_SIZE - CELL_SIZE * scale * 2) / 2);

        getChildren().addAll(circle);

        setOnMousePressed( e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });

    }

    public PinColor getColor() {
        return pinColor;
    }

    public void move(int x, int y) {
        oldX = x * CELL_SIZE;
        oldY = y * CELL_SIZE;
        relocate(oldX, oldY);
    }



}
