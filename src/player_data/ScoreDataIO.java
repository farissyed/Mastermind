package player_data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class with static functions to read and write score data
 */
public class ScoreDataIO {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

    public static void appendResult(GameResult gr) {

        try {
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

            if(gr.isGameWon() && gr.isDuplicatesAllowed() == duplicatesAllowed){
                sum += gr.getNumberOfTurns();
                count++;
            }
        }
        if(count != 0) {
            return sum / count;
        }
        return 0;
    }

    public static int getGamesPlayed() {
        GameResult[] results = getResults();
        return results.length;
    }

}
