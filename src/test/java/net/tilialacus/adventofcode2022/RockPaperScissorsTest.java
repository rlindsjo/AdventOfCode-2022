package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static net.tilialacus.adventofcode2022.RockPaperScissors.*;
import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {
    @Test
    void single() {
        assertAll(
                () -> assertEquals(8, play("A", "Y")),
                () -> assertEquals(1, play("B", "X")),
                () -> assertEquals(6, play("C", "Z")),
                () -> assertEquals(4, play("A", "X")),
                () -> assertEquals(1, play("B", "X")),
                () -> assertEquals(7, play("C", "X"))
        );
    }

    @Test
    void file() throws Exception {
        assertEquals(15, playAll("rock_paper_scissors_test.txt"));
    }
}