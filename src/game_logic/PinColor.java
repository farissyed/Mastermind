package game_logic;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PinColor {
    Red(Color.RED),
    Orange(Color.ORANGE),
    Yellow(Color.YELLOW),
    Green(Color.GREEN),
    Blue(Color.BLUE),
    Purple(Color.PURPLE),
    Black(Color.BLACK), // correct color and pos
    White(Color.WHITE); // correct color not pos

    final Color color;

    PinColor(Color color) {
        this.color = color;
    }

    private static final List<PinColor> GUESS = Collections.unmodifiableList(Arrays.asList(Red, Orange, Yellow, Green, Purple));
    private static final List<PinColor> FEEDBACK = Collections.unmodifiableList(Arrays.asList(Black, White));
    private static final int SIZE = GUESS.size();
    private static final Random RANDOM = new Random();

    public static PinColor randomPinColor()  {
        return GUESS.get(RANDOM.nextInt(SIZE));
    }


}
