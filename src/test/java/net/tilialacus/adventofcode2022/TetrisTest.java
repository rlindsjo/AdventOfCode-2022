package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisTest {

    @Test
    void drop() {
        Tetris tetris = new Tetris();
        tetris.drop(2022);
        assertEquals(3068, tetris.pit.firstEmpty());
    }
}