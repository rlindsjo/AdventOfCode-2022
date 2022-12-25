package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PipesTest {

    @Test
    void singleMover() {
        Pipes pipes = new Pipes();
        FileUtil.resourceLinesAsList("pipes_test.txt").forEach(pipes::add);
        Pipes.Move move = pipes.getBest(30);
        assertEquals(1651, move.score);
    }
}