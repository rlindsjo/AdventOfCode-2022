package net.tilialacus.adventofcode2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CaloriesParser {
    public static List<Elf> parse(String resource) throws Exception {
        var elfs = new ArrayList<Elf>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resource), StandardCharsets.UTF_8));
        var elf = new Elf();
        elfs.add(elf);
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.isEmpty()) {
                elf = new Elf();
                elfs.add(elf);
            } else {
                elf.addCalories(Integer.parseInt(line));
            }
        }
        return elfs;
    }
}
