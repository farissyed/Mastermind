package user_interface;

import game_logic.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player_data.GameResult;
import player_data.ScoreDataIO;

import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;

/**
 * This is the main class that begins the entire program
 */
public class MastermindApp extends Application {

    public static final int PIN_WIDTH = 50;
    public static final int PIN_HEIGHT = 50;

    private static CodePin[] code;

    private int attempts = 0;
    private static final int numTurns = 12;

    public static GridPane userGuessGrid;
    public static Button makeGuessButton;

    private BorderPane root;
    Stage welcomeStage;
    Stage gameStage;
    Stage winStage;
    Stage loseStage;

    public static boolean allowDuplicates;


    public static CodePin[] getCode() {
        return code;
    }

    public static void setCode(CodePin[] code) {
        MastermindApp.code = code;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param row - The row index
     * @param column - The column index
     * @param gridPane - The grid pane to look within
     * @return Returns the cell at specified position
     */
    public static Cell getCellAt(int row, int column, GridPane gridPane) {
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (Cell) node;
            }
        }

        return null;
    }

    /**
     * Creates the screen in which the game is played
     */
    public void createGameScreen() {

        root = new BorderPane();
        attempts = 0;

        userGuessGrid = new GridPane();
        makeGuessButton = new Button("Guess");

        code = Scorer.generateRandomCode(allowDuplicates);
        for (CodePin c : code) {
            System.out.println(c);
        }

        System.out.println();
        System.out.println();

        gameStage = new Stage();
        gameStage.setTitle("Mastermind");
        Scene scene = new Scene(root, 1200, 1000);


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
        top.setPadding(new Insets(20));
        top.setAlignment(Pos.CENTER);
        top.setBackground(new Background(new BackgroundFill(Color.color(.75, .75, .75), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setLeft(left);
        root.setCenter(center);
        root.setTop(top);


        Text makeGuessText = new Text("Make your guess:");
        makeGuessText.setStyle("-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, red 0%, orange 20%, yellow 40%, green 60%, blue 80% purple 100%);");
        makeGuessText.setFont(Font.font(50));
        userGuessGrid.setAlignment(Pos.CENTER);
        userGuessGrid.setPadding(new Insets(25, 5, 25, 5));
        userGuessGrid.getRowConstraints().add(new RowConstraints(PIN_HEIGHT));
        for (int i = 0; i < 5; i++) {
            userGuessGrid.getColumnConstraints().add(new ColumnConstraints(PIN_WIDTH));
            CodePinCell cp = new CodePinCell(PIN_WIDTH / 2.0, PIN_HEIGHT / 2.0, false);
            userGuessGrid.add(cp, i, 0);
        }

        makeGuessButton.setDisable(true);
        makeGuessButton.setStyle("-fx-cursor: hand; -fx-background-color: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        makeGuessButton.setPadding(new Insets(15, 22, 15, 22));
        makeGuessButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                attempts++;
                makeGuessButton.setDisable(true);
                CodePin[] userGuessCode = new CodePin[5];
                for (int i = 0; i < userGuessCode.length; i++) {
                    CodePinCell temp = ((CodePinCell) (getCellAt(0, i, userGuessGrid)));
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
                    FeedbackCell fc = ((FeedbackCell) (getCellAt(numTurns - attempts, i, left)));
                    fc.setVisible(true);
                    fc.setFeedbackPin(feedbackPins[i]);
                }


                CodePinCell[] displayUserGuessCode = new CodePinCell[5];
                for (int i = 0; i < displayUserGuessCode.length; i++) {
                    CodePinCell cpc = ((CodePinCell) (getCellAt(numTurns - attempts, i, center)));

                    cpc.setVisible(true);
                    cpc.setCodePin(userGuessCode[i]);
                }

                //check if code is correct
                if (Scorer.codeIsCorrect(feedbackPins)) {
                    //display "you win screen" and save game data
                    handleCorrectGuess();
                    return;
                }

                if (attempts >= numTurns) { //the user has lost the game
                    handleGameLost();
                }

            }
        });

        top.getChildren().addAll(makeGuessText, userGuessGrid, makeGuessButton);


        //Create grid of feedback pins
        int numFeedbackPins = 5; //number of columns for left section
        for (int j = 0; j < numTurns; j++) {
            left.getRowConstraints().add(new RowConstraints(PIN_HEIGHT));
        }


        //Add constraints to rows and columns in feedback area
        for (int i = 0; i < numFeedbackPins; i++) {
            left.getColumnConstraints().add(new ColumnConstraints(PIN_WIDTH));
            for (int j = 0; j < numTurns; j++) {
                FeedbackCell fc = new FeedbackCell(PIN_WIDTH / 2.0, PIN_HEIGHT / 2.0);
                fc.setVisible(false);
                left.add(fc, i, j);
            }
        }


        int numCodePins = 5; //number of columns for center section
        for (int j = 0; j < numTurns; j++) {
            center.getRowConstraints().add(new RowConstraints(PIN_HEIGHT));
        }

        //Add constraints to rows and columns in code pins area
        for (int i = 0; i < numCodePins; i++) {
            center.getColumnConstraints().add(new ColumnConstraints(PIN_WIDTH));
            for (int j = 0; j < numTurns; j++) {
                CodePinCell cpc = new CodePinCell(PIN_WIDTH / 2.0, PIN_HEIGHT / 2.0, true);
                cpc.setVisible(false);
                center.add(cpc, i, j);
            }
        }

        root.getChildren().addAll();

        gameStage.setScene(scene);
        gameStage.show();
    }


    /**
     * Creates the initial screen to welcome the user
     */
    public void createWelcomeScreen() {
        welcomeStage = new Stage();
        welcomeStage.setTitle("Welcome to Mastermind");
        VBox welcomeScreen = new VBox();
        welcomeScreen.setPadding(new Insets(15));
        welcomeScreen.setAlignment(Pos.CENTER);
        Scene scene = new Scene(welcomeScreen, 520, 600);
        Text title = new Text();
        title.setText("MASTERMIND");
        Font font = Font.font("Verdana", FontWeight.BOLD, 35);
        title.setFont(font);
        Label rules = new Label();

        rules.setText("The objective of the game is to determine the passcode generated \n" +
                "by 5 colored pegs.\n\n" +
                "Each peg will feature one of 7 different colors: \n" +
                "Red, Orange, Yellow, Green, Blue, Purple, Black\n\n" +
                "A peg can be of duplicate color within the code if you choose to allow \n" +
                "duplicates within the code.\n" +
                "\nChoose to allow duplicates within the Passcode: ");
        Label gameStats = new Label();
        gameStats.setText(
                "\n" +
                        "STATS\n" +
                        "\nGames Played:                                                                                      " + ScoreDataIO.getGamesPlayed() +
                        "\nAverage Attempts with Duplicates:                                                      " + (int) ScoreDataIO.getAverageAttempts(true) +
                        "\nAverage Attempts without Duplicates:                                                 " + (int) ScoreDataIO.getAverageAttempts(false) +
                        "");
        welcomeScreen.getChildren().add(title);
        /*Buttons */
        RadioButton duplicates = new RadioButton("Allow Duplicates");
        duplicates.setPadding(new Insets(15));
        duplicates.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                allowDuplicates = !allowDuplicates;
                System.out.println(allowDuplicates);
            }
        });

        welcomeScreen.getChildren().addAll(rules, duplicates, gameStats);

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);
        buttons.setPadding(new Insets(25));

        Button play = new Button("Start Game");
        play.setStyle("-fx-cursor: hand; -fx-background-color: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        play.setPrefHeight(50.0);
        play.setPrefWidth(120.0);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                welcomeStage.close();
                createGameScreen();

            }
        });
        Button quit = new Button("Quit");
        quit.setStyle("-fx-cursor: hand; -fx-background-color: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        setTopAnchor(quit, 435.0);
        setLeftAnchor(quit, 330.0);
        quit.setPrefWidth(120.0);
        quit.setPrefHeight(50.0);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                welcomeStage.close();
                System.exit(0);
            }
        });
        buttons.getChildren().addAll(play, quit);
        welcomeScreen.getChildren().addAll(buttons);

        welcomeStage.setScene(scene);
        welcomeStage.show();
    }

    /**
     * Generates the screen when the user wins the game
     */
    public void handleCorrectGuess() {
        //do everything for if user correctly guesses the code
        winStage = new Stage();
        AnchorPane playAgain = new AnchorPane();
        Scene scene = new Scene(playAgain, 300, 100);

        Label message = new Label();
        setTopAnchor(message, 15.0);
        setLeftAnchor(message, 75.0);
        winStage.setTitle("You Win!");
        message.setText("Congratulations, You Win!");

        Button play = new Button("Play Again");
        play.setStyle("-fx-background-color: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        setTopAnchor(play, 60.0);
        setLeftAnchor(play, 40.0);
        play.setPrefHeight(15.0);
        play.setPrefWidth(80.0);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameStage.close();
                winStage.close();
                createGameScreen();
            }
        });

        Button quit = new Button("Quit");
        quit.setStyle("-fx-background-color: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        setTopAnchor(quit, 60.0);
        setLeftAnchor(quit, 180.0);
        quit.setPrefHeight(15.0);
        quit.setPrefWidth(80.0);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        playAgain.getChildren().addAll(message, play, quit);

        winStage.setScene(scene);
        winStage.show();

        GameResult gr = new GameResult(code, attempts, allowDuplicates, true);
        ScoreDataIO.appendResult(gr);

    }

    /**
     * Generates the screen when the user loses the game
     */
    public void handleGameLost() {
        //do everything if player can't guess the code and uses up all their turns
        loseStage = new Stage();
        AnchorPane playAgain = new AnchorPane();
        Scene scene = new Scene(playAgain, 300, 100);

        Label message = new Label();
        message.setAlignment(Pos.CENTER);
        setTopAnchor(message, 15.0);
        setLeftAnchor(message, 100.0);
        loseStage.setTitle("You Lose");
        message.setText("Sorry, you lose.");

        Button play = new Button("Play Again");
        setTopAnchor(play, 60.0);
        setLeftAnchor(play, 40.0);
        play.setPrefHeight(15.0);
        play.setPrefWidth(80.0);
        play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameStage.close();
                loseStage.close();
                createGameScreen();
            }
        });

        Button quit = new Button("Quit");
        setTopAnchor(quit, 60.0);
        setLeftAnchor(quit, 180.0);
        quit.setPrefHeight(15.0);
        quit.setPrefWidth(80.0);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        playAgain.getChildren().addAll(message, play, quit);

        loseStage.setScene(scene);
        loseStage.show();

        GameResult gr = new GameResult(code, attempts, allowDuplicates, false);
        ScoreDataIO.appendResult(gr);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        createWelcomeScreen();
    }
}
