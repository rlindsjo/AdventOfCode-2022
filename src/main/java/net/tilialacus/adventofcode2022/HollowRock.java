package net.tilialacus.adventofcode2022;

import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.signum;

public class HollowRock {
    public static final Pos START_POS = new Pos(500, 0);
    boolean[][] grid = new boolean[1000][1000];

    public int drop() {
        int grains = 0;
        while(true) {
            try {
                Pos sand = START_POS;
                do {
                    sand = drop(sand);
                } while (sand != null);
                grains++;
            } catch (ArrayIndexOutOfBoundsException e) {
                return grains;
            }
        }
    }

    private Pos drop(Pos sand) {
        int y = sand.y + 1;
        if (!grid[sand.x][y]) {
            return new Pos(sand.x, y);
        } else if (!grid[sand.x - 1][y]) {
            return new Pos(sand.x - 1, y);
        } else if (!grid[sand.x + 1][y]) {
            return new Pos(sand.x + 1, y);
        } else {
            grid[sand.x][sand.y] = true;
            return null;
        }
    }

    private record Pos(int x, int y) {

        public static Pos of(String s) {
            String[] parts = s.split(",");
            return new Pos(parseInt(parts[0]), parseInt(parts[1]));
        }
    }

    public void path(String path) {
        Iterator<Pos> parts = Arrays.stream(path.split(" -> ")).map(Pos::of).toList().iterator();
        for (Pos start = parts.next(), next = null; parts.hasNext(); start = next) {
            next = parts.next();
            for (int x = start.x, y = start.y;
                 x != next.x || y != next.y;
                 x += signum(next.x - x), y += signum(next.y - y)) {
                grid[x][y] = true;
            }
            grid[next.x][next.y] = true;
        }
    }
}
