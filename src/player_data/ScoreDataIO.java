package player_data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.bcel.internal.classfile.Code;
import game_logic.CodePin;
import game_logic.Pin;
import game_logic.PinColor;
import org.hildan.fxgson.FxGson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class with static functions to read and write score data
 */
public class ScoreDataIO {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

    public static void appendResult(GameResult gr) {

        try {
//            reader = new FileReader("./game_results.json");
//            ArrayList<GameResult> results = gson.fromJson(reader, new TypeToken<ArrayList<GameResult>>(){}.getType());
//            reader.close();

            ArrayList<GameResult> results = new ArrayList<>(Arrays.asList(getResults()));

            if(results == null) {
                results = new ArrayList<>();
            }
            results.add(gr);
            FileWriter writer = new FileWriter("./game_results.json");
            gson.toJson(results.toArray(), writer);
            System.out.println(gson.toJson(results.toArray()));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameResult[] getResults() {
        FileReader reader = null;
        GameResult[] results = null;

        try {
            reader = new FileReader("./game_results.json");
            results = gson.fromJson(reader, GameResult[].class);
            reader.close();

            if(results == null) {
                results = new GameResult[0];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static double getAverageAttempts(boolean duplicatesAllowed) {
        GameResult[] results = getResults();

        double sum = 0;
        double count = 0;
        for (GameResult gr : results) {

            if(gr.isDuplicatesAllowed() == duplicatesAllowed){
                sum += gr.getNumberOfTurns();
                count++;
            }
        }

        return sum / count;
    }

    public static int getGamesPlayed() {
        GameResult[] results = getResults();

        return results.length;
    }

    public static void main(String[] args) {
//        inputData();
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new CodePin[] {
//                new CodePin(PinColor.randomPinColor()), new CodePin(PinColor.randomPinColor())
//        }));

        GameResult gr = new GameResult(
                new CodePin[]{
                        new CodePin(PinColor.Blue),
                        new CodePin(PinColor.Green),
                        new CodePin(PinColor.Red)
                }, 6, true);

//        appendResult(gr);
        System.out.println(getGamesPlayed());

    }

}
