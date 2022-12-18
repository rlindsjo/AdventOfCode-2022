package net.tilialacus.adventofcode2022;

import net.tilialacus.adventofcode2022.Rope.Pos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RopeTest {

    private Rope rope = new Rope();

    @Test
    void start() {
        assertEquals(new Pos(0,0), rope.getHead());
        assertEquals(new Pos(0,0), rope.getTail());
    }

    @Test
    void moveRightOne() {
        rope.move("R 1");
        assertEquals(new Pos(1,0), rope.getHead());
        assertEquals(new Pos(0,0), rope.getTail());
    }

    @Test
    void moveDownFive() {
        rope.move("D 5");
        assertEquals(new Pos(0,5), rope.getHead());
        assertEquals(new Pos(0,4), rope.getTail());
    }

    @Test
    void name() {
        FileUtil.resourceLinesAsList("rope_test.txt")
                .forEach(rope::move);
        assertEquals(13, rope.getVisited().size());
    }
}