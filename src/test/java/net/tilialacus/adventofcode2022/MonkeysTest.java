package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonkeysTest {

    @Test
    void parse() {
        Monkeys monkeys = new Monkeys();
        FileUtil.resourceLinesAsList("monkeys_test.txt")
                .forEach(monkeys::parse);
        assertEquals(152, monkeys.getMonkey("root").getValue());
    }
}