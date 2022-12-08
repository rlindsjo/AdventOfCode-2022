package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForrestTest {
    @Test
    void parse() {
        Forrest.parseTreeMap("tree_map_test.txt");
    }

    @Test
    void countVisibleTrees() {
        Forrest treeMap = Forrest.parseTreeMap("tree_map_test.txt");
        assertEquals(21, treeMap.visibleTrees());
    }

    @Test
    void scenic() {
        Forrest treeMap = Forrest.parseTreeMap("tree_map_test.txt");
        assertEquals(4, treeMap.scenic(1, 2));
    }

    @Test
    void bestScenic() {
        Forrest treeMap = Forrest.parseTreeMap("tree_map_test.txt");
        assertEquals(8, treeMap.bestScenic());
    }
}