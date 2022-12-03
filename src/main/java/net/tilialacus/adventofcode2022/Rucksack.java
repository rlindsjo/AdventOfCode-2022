package net.tilialacus.adventofcode2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Rucksack {
    private List<Compartment> compartments;

    public Rucksack(List<Compartment> compartments) {
        this.compartments = compartments;
    }

    public static Rucksack parseRucksack(String line) {
        return new Rucksack(
                List.of(
                        new Compartment(line.substring(0, line.length() / 2)),
                        new Compartment(line.substring(line.length() / 2))
                        ));
    }

    public static List<Rucksack> parseAll(String resource) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resource), StandardCharsets.UTF_8));
        var all = new ArrayList<Rucksack>();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            all.add(parseRucksack(line));
        }
        return all;
    }

    public static int priority(char itemType) {
        if (itemType > 'Z') {
            return itemType - 'a' + 1;
        } else {
            return itemType - 'A' + 27;
        }
    }

    public static char commonItem(Rucksack ...rucksacks) {
        outer: for (char c : rucksacks[0].getItems().toCharArray()) {
            for (int i = 1; i < rucksacks.length; i++) {
                if (rucksacks[i].getItems().indexOf(c) == -1) {
                    continue outer;
                }
            }
            return c;
        }
        throw new IllegalArgumentException();
    }

    public String getItems() {
        return compartments.get(0).getContent() + compartments.get(1).getContent();
    }

    public Compartment getCompartment(int index) {
        return compartments.get(index);
    }

    public char getCommonContent() {
        String other = getCompartment(1).getContent();
        for (char c : getCompartment(0).getContent().toCharArray()) {
            if (other.indexOf(c) != -1) {
                return c;
            }
        }
        throw new IllegalArgumentException("No common content");
    }

    public static class Compartment {
        private String content;

        public Compartment(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }
}
