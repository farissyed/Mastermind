package user_interface;

import com.sun.org.apache.bcel.internal.classfile.Code;
import game_logic.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

import java.util.Observable;

public class MastermindApp extends Application {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 8;

    private Cell[][] guessBoard = new Cell[WIDTH][HEIGHT];
    private Cell[][] feedbackBoard = new Cell[WIDTH][HEIGHT];
    private int numberOfGuesses = 0;


//    public static final int CELL_SIZE
    public static final int TOTAL_SIZE = 600;

    public static final int CODE_PIN_RADIUS = 30;
    public static final int FEEDBACK_PIN_RADIUS = 15;

    private Group cellGroup = new Group();
    private Group pinGroup = new Group();

//    Pane root = new Pane();

    private static CodePin[] code;

    public static CodePin[] getCode() {
        return code;
    }

    public static void setCode(CodePin[] code) {
        MastermindApp.code = code;
    }

    private BorderPane root;

    public Cell getCellAt (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return (Cell) result;
    }

    private void createContent(CodePin[] code) {
        root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        GridPane left = new GridPane(); //this is used for the feedback pins on the left
        left.setPadding(new Insets(15));
        left.setHgap(15);
        left.setVgap(10);
        GridPane center = new GridPane();
        center.setPadding(new Insets(15));
        center.setHgap(15);
        center.setVgap(10);
        root.setLeft(left);
        root.setCenter(center);

        final int feedbackPinWidth = 75;
        final int feedbackPinHeight = 75;

        int numTurns = 12;
        int numFeedbackPins = 5; //number of columns for left section
        for (int j = 0; j < numTurns; j++) {
            left.getRowConstraints().add(new RowConstraints(feedbackPinHeight));
        }

        for (int i = 0; i < numFeedbackPins; i++) {
            left.getColumnConstraints().add(new ColumnConstraints(feedbackPinWidth));
            for (int j = 0; j < numTurns; j++) {
                left.add(new FeedbackCell(feedbackPinWidth / 2.0, feedbackPinHeight / 2.0), i, j);
            }
        }


        final int codePinWidth = 75;
        final int codePinHeight = 75;

        int numCodePins = 5; //number of columns for left section
        for (int j = 0; j < numTurns; j++) {
            center.getRowConstraints().add(new RowConstraints(codePinHeight));
        }

        for (int i = 0; i < numCodePins; i++) {
            center.getColumnConstraints().add(new ColumnConstraints(codePinWidth));
            for (int j = 0; j < numTurns; j++) {
                center.add(new CodePinCell(codePinWidth / 2.0, codePinHeight / 2.0), i, j);
            }
        }

        Cell c = getCellAt(3, 3, center);

        if(c instanceof CodePinCell) {
            CodePin cp = new CodePin(PinColor.Blue);
            cp.setCircleCenter(codePinWidth / 2.0, codePinHeight / 2.0);
            ((CodePinCell)c).setFeedbackPin(cp);
        }


//        left.add(new FeedbackCell(), 0, 0);

//        setCode(code);
//
//        int cellSize = TOTAL_SIZE / 6;
//        root.setPrefSize(WIDTH * cellSize, HEIGHT * cellSize);
//        root.getChildren().addAll(cellGroup, pinGroup);
//
//        for (int x = 0; x < WIDTH; x++) {
//            for (int y = 0; y < HEIGHT; y++) {
//
//                Color c = x == 0 ? Color.valueOf("#814C1E") : Color.valueOf("#613814");
//
//                Cell cell = new Cell( c, x, y , cellSize);
//                cellGroup.getChildren().add(cell);
//
//                Pin pin = null;
//
//                if (y == 0 && x > 0) {
//                    pin = makePin(code[x-1].getColor(), x * cellSize, y * cellSize, cellSize);
//                }
//
//                if (pin != null) {
//                    cell.setPin(pin);
//                    pinGroup.getChildren().add(pin);
//                }
//            }
//        }

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
        int numOfPins = 5;
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

        Scene scene = new Scene(root, 1200, 900);
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
