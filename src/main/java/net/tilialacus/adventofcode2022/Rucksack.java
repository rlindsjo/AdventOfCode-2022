package net.tilialacus.adventofcode2022;

import java.util.List;

public class Rucksack {
    private List<Compartment> compartments;

    public Rucksack(List<Compartment> compartments) {
        this.compartments = compartments;
    }

    public static Rucksack parse(String line) {
        return new Rucksack(
                List.of(
                        new Compartment(line.substring(0, line.length() / 2)),
                        new Compartment(line.substring(line.length() / 2))
                        ));
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
