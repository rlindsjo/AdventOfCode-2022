package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static net.tilialacus.adventofcode2022.RockPaperScissors.playAll;
import static net.tilialacus.adventofcode2022.Rucksack.parseAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RucksackTest {
    @Test
    void compartments() {
        Rucksack rucksack = Rucksack.parse("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");

        assertEquals("wMqvLMZHhHMvwLH", rucksack.getCompartment(0).getContent());
        assertEquals("jbvcjnnSBnvTQFn", rucksack.getCompartment(1).getContent());
    }

    @Test
    void commonContents() {
        Rucksack rucksack = Rucksack.parse("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
        assertEquals('v', rucksack.getCommonContent());
    }

    @Test
    void priority() {
        assertEquals(22, Rucksack.priority('v'));
        assertEquals(27, Rucksack.priority('A'));
    }

    @Test
    void file() throws Exception {
        int sum = parseAll("rucksack_test.txt").stream()
                .map(Rucksack::getCommonContent)
                .mapToInt(Rucksack::priority)
                .sum();
        assertEquals(157, sum);
    }
}