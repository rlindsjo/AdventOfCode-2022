package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaloriesParserTest {
    @Test
    void caloriesParser() throws Exception {
        List<Elf> elfs = CaloriesParser.parse("calories_test.txt");

        assertEquals(5, elfs.size());
        assertEquals(11000, elfs.get(2).getCalories());
    }
}
