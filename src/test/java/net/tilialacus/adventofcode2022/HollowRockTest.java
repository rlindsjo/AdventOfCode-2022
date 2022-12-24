package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HollowRockTest {

    @Test
    void build() {
        HollowRock hollowRock = new HollowRock();
        List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"
        ).forEach(
            hollowRock::path
        );
        assertTrue(hollowRock.grid[500][9]);
    }

    @Test
    void drop() {
        HollowRock hollowRock = new HollowRock();
        List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"
        ).forEach(
                hollowRock::path
        );
        int grains = hollowRock.drop();
        assertEquals(24, grains);
    }
}