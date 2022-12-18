package net.tilialacus.adventofcode2022;

import net.tilialacus.adventofcode2022.Rope.Pos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RopeTest {

    private Rope rope = new Rope();

    @Test
    void start() {
        assertEquals(new Pos(0,0), rope.getHead().getPos());
        assertEquals(new Pos(0,0), rope.getTail().getPos());
    }

    @Test
    void moveRightOne() {
        rope.move("R 1");
        assertEquals(new Pos(1,0), rope.getHead().getPos());
        assertEquals(new Pos(0,0), rope.getTail().getPos());
    }

    @Test
    void moveDownFive() {
        rope.move("D 5");
        assertEquals(new Pos(0,5), rope.getHead().getPos());
        assertEquals(new Pos(0,4), rope.getTail().getPos());
    }

    @Test
    void singleRope() {
        FileUtil.resourceLinesAsList("rope_test.txt")
                .forEach(rope::move);
        assertEquals(13, rope.getVisited().size());
    }

    @Test
    void longRope() {
        rope = new Rope(10);
        FileUtil.resourceLinesAsList("rope_test_2.txt")
                .forEach(rope::move);
        assertEquals(36, rope.getVisited().size());
    }

}