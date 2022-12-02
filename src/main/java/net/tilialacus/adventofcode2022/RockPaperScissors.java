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
            System.err.println("%s %s".formatted(++lines, score) );
        }
        return score;
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
            case "X" -> 1;
            case "Y" -> 2;
            case "Z" -> 3;
            default -> throw new IllegalArgumentException(shape);
        };
    }
}
