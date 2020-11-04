package user_interface;

import game_logic.Cell;
import game_logic.CodePin;
import game_logic.Pin;
import game_logic.PinColor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MastermindApp extends Application {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 8;

    private Cell[][] guessBoard = new Cell[WIDTH][HEIGHT];
    private Cell[][] feedbackBoard = new Cell[WIDTH][HEIGHT];


    public static final int CELL_SIZE = 100;

    public static final int CODE_PIN_RADIUS = 30;
    public static final int FEEDBACK_PIN_RADIUS = 30;

    private Group cellGroup = new Group();
    private Group pinGroup = new Group();

    private static CodePin[] code;

    public static CodePin[] getCode() {
        return code;
    }

    public static void setCode(CodePin[] code) {
        MastermindApp.code = code;
    }

    private Parent createContent(CodePin[] code) {
        setCode(code);
        Pane root = new Pane();
        root.setPrefSize(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);
        root.getChildren().addAll(cellGroup, pinGroup);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {

                Color c = x == 0 ? Color.valueOf("#814C1E") : Color.valueOf("#613814");

                Cell cell = new Cell( c, x, y );
                cellGroup.getChildren().add(cell);

                Pin pin = null;

                if (y == 0 && x > 0) {
                    pin = makePin(code[x-1].getColor(), x, y);
                }

                if (pin != null) {
                    cell.setPin(pin);
                    pinGroup.getChildren().add(pin);
                }
            }
        }

        return root;
    }

    private Pin makePin(PinColor c, int x, int y) {
        Pin pin = new Pin(c, x ,y);

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

        Scene scene = new Scene(createContent(testCode));
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
