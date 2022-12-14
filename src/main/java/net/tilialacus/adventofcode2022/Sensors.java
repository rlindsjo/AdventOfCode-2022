package net.tilialacus.adventofcode2022;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class Sensors {
    private static Pattern parser = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)$");

    public static int covered(List<Sensor> sensors, int row) {
        int reduced = getRanges(sensors, row).ranges.stream().mapToInt(Sensors.Range::size).sum();
        int beacons = (int) sensors.stream()
                .filter(it -> it.by == row)
                .map(it -> it.bx)
                .distinct()
                .count();
        return reduced - beacons;

    }

    public static Ranges getRanges(List<Sensor> sensors, int row) {
        List<Sensors.Range> excluded = sensors
                .stream()
                .map(it -> it.covered(row))
                .sorted()
                .toList();
        return reduce(excluded);
    }

    public static class Ranges {
        List<Range> ranges = new ArrayList<>();

        public void add(Range range) {
            ranges.add(range);
        }
    }

    public record Range(int start, int end) implements Comparable<Range> {
        public static Range EMPTY = new Range(0,0);

        public int size() {
            return end - start + 1;
        }

        @Override
        public int compareTo(Range o) {
            return start - o.start;
        }
    }
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

        public Range covered(int row) {
            int dx = distance(bx, by) - distance(sx, row);
            if (dx >= 0) {
                return new Range(sx - dx, sx + dx);
            } else {
                return Range.EMPTY;
            }
        }
    }

    public static Ranges reduce(List<Range> input) {
        Ranges ranges = new Ranges();
        List<Sensors.Range> reduced = new ArrayList<>();
        Iterator<Range> iterator = input.iterator();
        Sensors.Range current = iterator.next();
        while (iterator.hasNext()) {
            Sensors.Range next = iterator.next();
            if (current == Range.EMPTY) {
                current = next;
            } else if (next == Range.EMPTY){
                // Ignore
            } else if (current.end() >= next.start()) {
                current = new Sensors.Range(current.start(), max(current.end(), next.end()));
            } else {
                ranges.add(current);
                current = next;
            }
        }
        ranges.add(current);
        return ranges;
    }
}
