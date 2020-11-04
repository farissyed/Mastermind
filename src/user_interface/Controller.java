package user_interface;

import game_logic.CodePin;
import game_logic.Scorer;
import javafx.fxml.FXML;

import javafx.scene.control.CheckBox;

public class Controller {
    @FXML private CheckBox allowDuplicatesCheckBox;

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

        Main.setCode(codePins);
        CodePin[] c = Main.getCode();

        for (CodePin cp :
                c) {
            System.out.println(cp.getColor());
        }
    }
}
