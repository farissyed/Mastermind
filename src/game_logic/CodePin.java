package game_logic;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CodePin extends Pin {


    public CodePin(PinColor c) {
        super(c, 30);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

}
