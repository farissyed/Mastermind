package user_interface;

import game_logic.Cell;
import game_logic.CodePin;
import game_logic.Pin;
import game_logic.PinColor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;

public class MastermindApp extends Application {

    public static final int WIDTH = 5;
    public static final int HEIGHT = 8;

    private Cell[][] guessBoard = new Cell[WIDTH][HEIGHT];
    private Cell[][] feedbackBoard = new Cell[WIDTH][HEIGHT];


    public static final int CELL_SIZE = 75;

    public static final int CODE_PIN_RADIUS = 30;
    public static final int FEEDBACK_PIN_RADIUS = 30;

    private Group cellGroup = new Group();
    private Group pinGroup = new Group();

    private static CodePin[] code;
    private static CodePin[] guessCode;

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

                Color c = (x + y) % 2 == 0 ? Color.valueOf("#814C1E") : Color.valueOf("#613814");

                Cell cell = new Cell( c, x, y );
                cellGroup.getChildren().add(cell);

                Pin pin = null;

                if (y == 0) pin = makePin(code[x].getColor(), x, y);


                if (pin != null) {
                    cell.setPin(pin);
                    pinGroup.getChildren().add(pin);
                }
            }
        }

        return root;
    }

    private int toBoard(double pixel) {
        return (int)(pixel + CELL_SIZE / 2) / CELL_SIZE;
    }

    private Pin makePin(PinColor c, int x, int y) {
        Pin pin = new Pin(c, x ,y);

        pin.setOnMouseReleased(e -> {

        });

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
//
//        randomCode();
//        CodePin[] testCode = getCode();

////        Scene scene = new Scene(createContent(testCode));
//        primaryStage.setTitle("Mastermind");
////        Scene menuPage = new Scene(root, 800  , 600);
////        menuPage.getStylesheets().add("user_interface/menuPage.css");
//        primaryStage.setScene(scene);
//        primaryStage.show();

        createGameScreen();

    }


    public void createGameScreen() {
        Stage stage = new Stage();
        stage.setTitle("Mastermind Game");
        AnchorPane gameScreen = new AnchorPane();
        Scene scene = new Scene(gameScreen, 1000, 800);

        HBox options = new HBox();
        options.setSpacing(25);

        for (int i = 0; i < 5; i++) {
            Circle c = new Circle(20.0);
            options.getChildren().add(c);
            int finalI = i;
            c.setOnMouseClicked(e -> {
                toggleColor(c, finalI);
            });
        }
        setTopAnchor(options, 30.0);
        setLeftAnchor(options, 270.0);

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {

        });
        submit.setPrefHeight(50.0);
        submit.setPrefWidth(120.0);
        setTopAnchor(submit, 25.0);
        setLeftAnchor(submit, 625.0);

        Button exit = new Button("Exit");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        exit.setPrefHeight(50.0);
        exit.setPrefWidth(120.0);
        setTopAnchor(exit, 25.0);
        setLeftAnchor(exit, 780.0);

        gameScreen.getChildren().addAll(options, submit, exit);
        stage.setScene(scene);
        stage.show();

    }

    public void toggleColor(Circle c, int index) {
        PinColor[] p = PinColor.values();

        if (index == p.length) {
            index = 0;
        } else {
            index++;
        }

        Color color = p[index].getColor();
        c.setFill(color);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
