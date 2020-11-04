package user_interface;

import game_logic.CodePin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static CodePin[] code;

    public static CodePin[] getCode() {
        return code;
    }

    public static void setCode(CodePin[] c) {
        code = c;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{ //this is where the program flow basically starts
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Mastermind");
        Scene menuPage = new Scene(root, 1500  , 1000);
        menuPage.getStylesheets().add("user_interface/menuPage.css");

        primaryStage.setScene(menuPage);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
