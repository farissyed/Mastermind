package game_logic;

import com.google.gson.annotations.Expose;
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

    @Expose(serialize = true, deserialize = true)
    final Color pinColor;

    PinColor(Color pinColor) {
        this.pinColor = pinColor;
    }

    public Color getPinColor() {
        return pinColor;
    }

    public static final List<PinColor> GUESS = Collections.unmodifiableList(Arrays.asList(Red, Orange, Yellow, Green, Blue, Purple, Black));
    private static final List<PinColor> FEEDBACK = Collections.unmodifiableList(Arrays.asList(Black, White));
    public static final int SIZE = GUESS.size();
    private static final Random RANDOM = new Random();

    public static PinColor randomPinColor()  {
        return GUESS.get(RANDOM.nextInt(SIZE));
    }


}
