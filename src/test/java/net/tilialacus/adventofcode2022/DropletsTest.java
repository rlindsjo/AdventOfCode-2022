package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DropletsTest {

    @Test
    void surface() {
        Droplets droplets = new Droplets();
        FileUtil.resourceLinesAsList("droplets_test.txt")
                .forEach(droplets::parse);
        assertEquals(64, droplets.surfaceArea());
    }

    @Test
    void outsideSurface() {
        Droplets droplets = new Droplets();
        "1,1,1".lines().forEach(droplets::parse);
        assertEquals(6, droplets.outsideSurfaceArea());
    }

    @Test
    void outsideSurface2() {
        Droplets droplets = new Droplets();
        FileUtil.resourceLinesAsList("droplets_test.txt")
                .forEach(droplets::parse);
        assertEquals(58, droplets.outsideSurfaceArea());
    }
}