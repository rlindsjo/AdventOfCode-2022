package net.tilialacus.adventofcode2022;

import java.util.Collections;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.Math.*;

public class Sensors {
    private static Pattern parser = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)$");

    public static Sensor parse(String input) {
        Matcher matcher = parser.matcher(input);
        matcher.find();
        return new Sensor(
                parseInt(matcher.group(1)),
                parseInt(matcher.group(2)),
                parseInt(matcher.group(3)),
                parseInt(matcher.group(4))
        );
    }

    public static class Sensor {

        final int sx;
        final int sy;
        final int bx;
        final int by;

        public Sensor(int sx, int sy, int bx, int by) {
            this.sx = sx;
            this.sy = sy;
            this.bx = bx;
            this.by = by;
        }

        public boolean noBeacon(int x, int y) {
            return distance(x,y) < distance(bx, by);
        }

        private int distance(int x, int y) {
            return abs(x - sx) + abs(y - sy);
        }

        public Set<Integer> notInRow(int row) {
            int dx = distance(bx, by) - distance(sx, row);
            if (dx >= 0) {
                return IntStream.range(sx - dx, sx + dx + 1).boxed().collect(Collectors.toSet());
            } else {
                return Collections.emptySet();
            }
        }
    }
}
