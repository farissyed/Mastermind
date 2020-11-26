package user_interface;

import game_logic.Cell;
import game_logic.CodePin;
import game_logic.Pin;
import game_logic.PinColor;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Observable;

public class MastermindApp extends Application {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 8;

    private Cell[][] guessBoard = new Cell[WIDTH][HEIGHT];
    private Cell[][] feedbackBoard = new Cell[WIDTH][HEIGHT];


//    public static final int CELL_SIZE
    public static final int TOTAL_SIZE = 600;

    public static final int CODE_PIN_RADIUS = 30;
    public static final int FEEDBACK_PIN_RADIUS = 15;

    private Group cellGroup = new Group();
    private Group pinGroup = new Group();

    Pane root = new Pane();

    private static CodePin[] code;

    public static CodePin[] getCode() {
        return code;
    }

    public static void setCode(CodePin[] code) {
        MastermindApp.code = code;
    }

    private void createContent(CodePin[] code) {
        setCode(code);

        int cellSize = TOTAL_SIZE / 6;
        root.setPrefSize(WIDTH * cellSize, HEIGHT * cellSize);
        root.getChildren().addAll(cellGroup, pinGroup);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {

                Color c = x == 0 ? Color.valueOf("#814C1E") : Color.valueOf("#613814");

                Cell cell = new Cell( c, x, y , cellSize);
                cellGroup.getChildren().add(cell);

                Pin pin = null;

                if (y == 0 && x > 0) {
                    pin = makePin(code[x-1].getColor(), x * cellSize, y * cellSize, cellSize);
                }

                if (pin != null) {
                    cell.setPin(pin);
                    pinGroup.getChildren().add(pin);
                }
            }
        }

    }

    private Cell getCellFor(int x, int y) {
        ObservableList cells = cellGroup.getChildren();

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = (Cell)cells.get(i);
            if(cell.getCellX() == x && cell.getCellY() == y) {
                return cell;
            }
        }
        return null;
    }

    private void createDraggablePins() {
        int numOfPins = PinColor.SIZE;
        int cellSize = TOTAL_SIZE / numOfPins;
        for (int i = 0; i < PinColor.GUESS.size(); i++) {
            Pin pin = makePin(PinColor.GUESS.get(i), (i + 1) * cellSize, HEIGHT * cellSize, cellSize);
            pinGroup.getChildren().add(pin);

        }
    }

    private void createGuessOptionSlots() {

    }

    private Pin makePin(PinColor c, int x, int y, int cellSize) {
        Pin pin = new Pin(c, x ,y, cellSize);

        return pin;
    }

    private void randomCode() {

        CodePin[] testCode = new CodePin[5];

        for (int i = 0; i < testCode.length; i++) {
            testCode[i] = new CodePin(PinColor.randomPinColor());
        }

        setCode(testCode);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        randomCode();
        CodePin[] testCode = getCode();

        createContent(testCode);
        createDraggablePins();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Mastermind");
//        Scene menuPage = new Scene(root, 800  , 600);
//        menuPage.getStylesheets().add("user_interface/menuPage.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
