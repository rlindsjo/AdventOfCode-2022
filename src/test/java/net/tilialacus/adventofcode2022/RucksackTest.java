package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

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
}