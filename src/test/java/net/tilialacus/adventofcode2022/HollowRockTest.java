package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

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
    void dropToFloor() {
        HollowRock hollowRock = new HollowRock();
        List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"
        ).forEach(
                hollowRock::path
        );
        int grains = hollowRock.dropToFloor();
        assertEquals(24, grains);
    }

    @Test
    void dropTofull() {
        HollowRock hollowRock = new HollowRock();
        List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"
        ).forEach(
                hollowRock::path
        );
        int grains = hollowRock.dropToFull();
        assertEquals(93, grains);
    }
}
