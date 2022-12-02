package net.tilialacus.adventofcode2022;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) throws Exception {
        List<Elf> elfs = CaloriesParser.parse("day1/calories.txt");
        elfs.sort(Comparator.comparing(Elf::getCalories).reversed());
        System.out.println("Top elf calories %s".formatted(elfs.get(0).getCalories()));
        System.out.println("Top 3 elf calories %s".formatted(elfs.stream().limit(3).collect(Collectors.summingInt(Elf::getCalories))));
    }
}
