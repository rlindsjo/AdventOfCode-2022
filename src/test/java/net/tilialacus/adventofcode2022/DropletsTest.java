package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DropletsTest {

    @Test
    void parse() {
        Droplets droplets = new Droplets();
        FileUtil.resourceLinesAsList("droplets_test.txt")
                .forEach(droplets::parse);
        assertEquals(64, droplets.surfaceArea());
    }
}