package game_logic;

import com.google.gson.annotations.Expose;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * Enum to represent the
 */
public enum PinColor {
    Red(Color.RED),
    Orange(Color.ORANGE),
    Yellow(Color.YELLOW),
    Green(Color.GREEN),
    Blue(Color.BLUE),
    Purple(Color.PURPLE),
    Black(Color.BLACK), // correct color and pos
    White(Color.WHITE); // correct color not pos

    @Expose(serialize = true, deserialize = true)
    final Color pinColor;

    PinColor(Color pinColor) {
        this.pinColor = pinColor;
    }

    public Color getPinColor() {
        return pinColor;
    }

    public static final ArrayList<PinColor> GUESS = new ArrayList<>(Arrays.asList(Red, Orange, Yellow, Green, Blue, Purple, Black));
    private static final List<PinColor> FEEDBACK = new ArrayList<>(Arrays.asList(Black, White));
    public static final int SIZE = GUESS.size();
    private static final Random RANDOM = new Random();
    public static final PinColor CORRECT_POSITION_COLOR = Red; //correct color and position
    public static final PinColor CORRECT_COLOR_COLOR = Black; //correct color but not position

    public static PinColor randomPinColor()  {
        return GUESS.get(RANDOM.nextInt(SIZE));
    }


}
