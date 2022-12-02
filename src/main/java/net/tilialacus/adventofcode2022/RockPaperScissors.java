package net.tilialacus.adventofcode2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RockPaperScissors {

    public static int play(String opponent, String you) {
        return shapeScore(you) + matchScore(opponent, you);
    }

    public static int playAll(String resource) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resource), StandardCharsets.UTF_8));
        int lines = 0;
        int score = 0;
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] shapes = line.split(" ");
            score += play(shapes[0], shapes[1]);
        }
        return score;
    }

    public static int playAllOptimal(String resource) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resource), StandardCharsets.UTF_8));
        int lines = 0;
        int score = 0;
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] shapes = line.split(" ");
            score += playOptimal(shapes[0], shapes[1]);
        }
        return score;
    }

    public static int playOptimal(String opponent, String outcome) {
        return play(opponent, optimalMove(opponent, outcome));
    }

    private static String optimalMove(String opponent, String outcome) {
        var you = switch (opponent + outcome) {
            case "AX" -> "C";
            case "BX" -> "A";
            case "CX" -> "B";
            case "AY" -> "A";
            case "BY" -> "B";
            case "CY" -> "C";
            case "AZ" -> "B";
            case "BZ" -> "C";
            case "CZ" -> "A";
            default -> throw new IllegalArgumentException(opponent + " " + outcome);
        };
        return you;
    }

    private static int matchScore(String opponent, String you) {
        int rankOpponent = rank(opponent);
        int rankYou = rank(you);
        if (rankYou == rankOpponent) {
            return 3;
        } else if ((rankOpponent + 1) % 3 == rankYou) {
            return 6;
        } else {
            return 0;
        }
    }

    public static int rank(String shape) {
        return switch (shape) {
            case "X", "A" -> 0;
            case "Y", "B" -> 1;
            case "Z", "C" -> 2;
            default -> throw new IllegalArgumentException(shape);
        };
    }

    private static int shapeScore(String shape) {
        return switch (shape) {
            case "X", "A" -> 1;
            case "Y", "B" -> 2;
            case "Z", "C" -> 3;
            default -> throw new IllegalArgumentException(shape);
        };
    }
}
