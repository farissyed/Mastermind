package user_interface;

import game_logic.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MastermindApp extends Application {

    public static final int PIN_WIDTH = 50;
    public static final int PIN_HEIGHT = 50;

    public static final int WIDTH = 6;
    public static final int HEIGHT = 8;
    public static final int TOTAL_SIZE = 600;

    private static CodePin[] code;

    private int attempts = 0;
    private static int numTurns = 12;

    private final Group pinGroup = new Group();
    private BorderPane root;

    public static CodePin[] getCode() {
        return code;
    }

    public static void setCode(CodePin[] code) {
        MastermindApp.code = code;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Cell getCellAt (int row, int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                return (Cell)node;
            }
        }

        return null;
    }

    private void createContent() {
        root = new BorderPane();
        root.setPadding(new Insets(10));
        GridPane left = new GridPane(); //this is used for the feedback pins on the left
        left.setPadding(new Insets(15));
        left.setHgap(15);
        left.setVgap(5);
        GridPane center = new GridPane();
        center.setPadding(new Insets(15));
        center.setHgap(15);
        center.setVgap(5);
        VBox top = new VBox();
        top.setPadding(new Insets(10));
        top.setAlignment(Pos.CENTER);
        root.setLeft(left);
        root.setCenter(center);
        root.setTop(top);


        Text makeGuessText = new Text("Make your guess:");
        GridPane userGuessGrid = new GridPane();
        userGuessGrid.setAlignment(Pos.CENTER);
        userGuessGrid.getRowConstraints().add(new RowConstraints(PIN_HEIGHT));
        for (int i = 0; i < 5; i++) {
            userGuessGrid.getColumnConstraints().add(new ColumnConstraints(PIN_WIDTH));
            CodePinCell cp = new CodePinCell(PIN_WIDTH / 2.0, PIN_HEIGHT / 2.0, false);
            userGuessGrid.add(cp, i, 0);
        }
        Button makeGuessButton = new Button("Guess");
        makeGuessButton.setPadding(new Insets(15));
        makeGuessButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CodePin[] userGuessCode = new CodePin[5];
                for (int i = 0; i < userGuessCode.length; i++) {
                    CodePinCell temp = ((CodePinCell)(getCellAt(0, i, userGuessGrid)));
                    userGuessCode[i] = new CodePin(temp.getPin().getColor());
                    userGuessCode[i].setCircleCenter(temp.getPin().getCenterX(), temp.getPin().getCenterY());
                    temp.clearPin();
                }
                FeedbackPin[] feedbackPins = Scorer.guess(userGuessCode);
                for (int i = 0; i < feedbackPins.length; i++) {
                    System.out.println(feedbackPins[i]);
                }
                System.out.println();

                for (int i = 0; i < feedbackPins.length; i++) {
                    FeedbackCell fc = ((FeedbackCell)(getCellAt(numTurns - attempts - 1, i, left)));
                    fc.setVisible(true);
                    fc.setFeedbackPin(feedbackPins[i]);
                }


                CodePinCell[] displayUserGuessCode = new CodePinCell[5];
                for (int i = 0; i < displayUserGuessCode.length; i++) {
                    CodePinCell cpc = ((CodePinCell)(getCellAt(numTurns - attempts - 1, i, center)));

                    cpc.setVisible(true);
                    cpc.setCodePin(userGuessCode[i]);
                }

                //check if code is correct
                if(Scorer.codeIsCorrect(feedbackPins)) {
                    //display "you win screen" and save game data
                    handleCorrectGuess();
                    return;
                }
                attempts++;
                if(attempts >= numTurns) {
                    handleGameLost();
                }

            }
        });

        top.getChildren().addAll(makeGuessText, userGuessGrid, makeGuessButton);


        int numFeedbackPins = 5; //number of columns for left section
        for (int j = 0; j < numTurns; j++) {
            left.getRowConstraints().add(new RowConstraints(PIN_HEIGHT));
        }

        for (int i = 0; i < numFeedbackPins; i++) {
            left.getColumnConstraints().add(new ColumnConstraints(PIN_WIDTH));
            for (int j = 0; j < numTurns; j++) {
                FeedbackCell fc =new FeedbackCell(PIN_WIDTH / 2.0, PIN_HEIGHT / 2.0);
                fc.setVisible(false);
                left.add(fc, i, j);
            }
        }


        int numCodePins = 5; //number of columns for left section
        for (int j = 0; j < numTurns; j++) {
            center.getRowConstraints().add(new RowConstraints(PIN_HEIGHT));
        }

        for (int i = 0; i < numCodePins; i++) {
            center.getColumnConstraints().add(new ColumnConstraints(PIN_WIDTH));
            for (int j = 0; j < numTurns; j++) {
                CodePinCell cpc = new CodePinCell(PIN_WIDTH / 2.0, PIN_HEIGHT / 2.0, true);
                cpc.setVisible(false);
                center.add(cpc, i, j);
            }
        }
    }


    public void handleCorrectGuess() {
        //do everything for if user correctly guesses the code

    }
    public void handleGameLost() {
        //do everything if player can't guess the code and uses up all their turns
    }

    private void createDraggablePins() {
        int numOfPins = PinColor.SIZE;
        int cellSize = TOTAL_SIZE / numOfPins;
        for (int i = 0; i < PinColor.GUESS.size(); i++) {
            Pin pin = makePin(PinColor.GUESS.get(i), (i + 1) * cellSize, HEIGHT * cellSize, cellSize);
            pinGroup.getChildren().add(pin);

        }
    }

    private Pin makePin(PinColor c, int x, int y, int cellSize) {
        Pin pin = new Pin(c, x ,y, cellSize);
        return pin;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        code = Scorer.generateRandomCode(false);
        for (CodePin c :
                code) {
            System.out.println(c);
        }
        System.out.println();
        System.out.println();

        createContent();
        createDraggablePins();

        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setTitle("Mastermind");

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
