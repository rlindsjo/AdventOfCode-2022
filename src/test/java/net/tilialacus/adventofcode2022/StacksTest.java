package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StacksTest {
        Stacks stacks = Stacks.parseStacks(
            """
                [D]   \s
            [N] [C]   \s
            [Z] [M] [P]
             1   2   3\s
            """);
    @Test
    void parse() {
        assertEquals('D', stacks.get(1));
    }

    @Test
    void move() {
        stacks.move("move 1 from 2 to 1");
        stacks.move("move 3 from 1 to 3");
        stacks.move("move 2 from 2 to 1");
        stacks.move("move 1 from 1 to 2");
        assertEquals("CMZ", stacks.getTop());
    }

    @Test
    void moveAsOne() {
        stacks.moveAsOne("move 1 from 2 to 1");
        stacks.moveAsOne("move 3 from 1 to 3");
        stacks.moveAsOne("move 2 from 2 to 1");
        stacks.moveAsOne("move 1 from 1 to 2");
        assertEquals("MCD", stacks.getTop());
    }
}