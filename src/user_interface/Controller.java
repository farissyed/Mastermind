package user_interface;

import game_logic.CodePin;
import game_logic.Scorer;
import javafx.fxml.FXML;

import javafx.scene.control.CheckBox;

public class Controller {
    @FXML private CheckBox allowDuplicatesCheckBox;

    /**
     * Displays in console, prints out the random code generated by computer for the current game when game starts
     */
    public void onStartGame() {
        System.out.println("Starting game");
        CodePin[] codePins;
        if(allowDuplicatesCheckBox.isSelected()) { //if checked
            codePins = Scorer.generateRandomCode(true);
        }
        else {
            codePins = Scorer.generateRandomCode(false);
        }

        System.out.println(allowDuplicatesCheckBox.isSelected());

        MastermindApp.setCode(codePins);
        CodePin[] c = MastermindApp.getCode();

        for (CodePin cp :
                c) {
            System.out.println(cp.getColor());
        }
    }
}
