package net.tilialacus.adventofcode2022;

import java.util.List;

import static net.tilialacus.adventofcode2022.Rucksack.parseAll;

public class Day3 {
    public static void main(String[] args) throws Exception {
        List<Rucksack> rucksacks = parseAll("day3/rucksack.txt");
        int sum = rucksacks.stream()
                .map(Rucksack::getCommonContent)
                .mapToInt(Rucksack::priority)
                .sum();
        System.out.println("Priority of rucksacks is " + sum);

        sum = 0;
        for (int i = 0; i < rucksacks.size(); i+=3) {
            sum += Rucksack.priority(
                    Rucksack.commonItem(
                            rucksacks.get(i),
                            rucksacks.get(i + 1),
                            rucksacks.get(i + 2)
                    )
            );
        }
        System.out.println("Priority of rucksack groups is " + sum);
    }
}
