package net.tilialacus.adventofcode2022;

import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.signum;

public class HollowRock {
    public static final Pos START_POS = new Pos(500, 0);
    private int floor = 0;
    boolean[][] grid = new boolean[1000][1000];

    public int dropToFull() {
        int grains = 0;
        do {
            grains++;
        } while (!START_POS.equals(fallUntilRest(START_POS)));
        return grains;
    }

    public int dropToFloor() {
        int grains = 0;
        while (!onFloor(fallUntilRest(START_POS))) {
            grains++;
        }
        return grains;
    }

    private Pos fallUntilRest(Pos sand) {
        Pos next = sand;
        do {
            sand = next;
            next = fall(sand);
        } while (!next.equals(sand));
        return next;
    }

    private boolean onFloor(Pos pos) {
        return pos.y + 1 == floor;
    }

    private Pos fall(Pos sand) {
        int y = sand.y + 1;
        if (y == floor) {
            grid[sand.x][sand.y] = true;
            return sand;
        } else if (!grid[sand.x][y]) {
            return new Pos(sand.x, y);
        } else if (!grid[sand.x - 1][y]) {
            return new Pos(sand.x - 1, y);
        } else if (!grid[sand.x + 1][y]) {
            return new Pos(sand.x + 1, y);
        } else {
            grid[sand.x][sand.y] = true;
            return sand;
        }
    }

    private record Pos(int x, int y) {
        public static Pos of(String s) {
            String[] parts = s.split(",");
            return new Pos(parseInt(parts[0]), parseInt(parts[1]));
        }

        public Pos towards(Pos next) {
            return new Pos(x + signum(next.x - x), y + signum(next.y - y));
        }
    }

    public void path(String path) {
        Iterator<Pos> parts = Arrays.stream(path.split(" -> ")).map(Pos::of).toList().iterator();
        for (Pos start = parts.next(), next = null; parts.hasNext(); start = next) {
            next = parts.next();
            for (Pos pos = start;
                 !pos.equals(next);
                 pos = pos.towards(next)) {
                mark(pos);
            }
            mark(next);
        }
    }

    private void mark(Pos pos) {
        grid[pos.x][pos.y] = true;
        if (pos.y + 2 > floor) {
            floor = pos.y + 2;
        }
    }
}
