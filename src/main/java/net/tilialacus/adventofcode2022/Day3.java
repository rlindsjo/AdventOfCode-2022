package net.tilialacus.adventofcode2022;

import static net.tilialacus.adventofcode2022.Rucksack.parseAll;

public class Day3 {
    public static void main(String[] args) throws Exception {
        int sum = parseAll("day3/rucksack.txt").stream()
                .map(Rucksack::getCommonContent)
                .mapToInt(Rucksack::priority)
                .sum();
        System.out.print("Priority of rucksacks is " + sum);
    }
}
