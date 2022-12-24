package net.tilialacus.adventofcode2022;

import java.util.*;

public class Day15 {

    public static void main(String[] args) {
        List<Sensors.Sensor> sensors = FileUtil.resourceLinesAsList("day15/sensors.txt").stream()
                .map(Sensors::parse).toList();
        System.err.println("Excluded " + Sensors.covered(sensors,  2000000));

        for (int row = 0; row < 4_000_000; row ++) {
            Sensors.Ranges covers = Sensors.getRanges(sensors, row);
            if (covers.ranges.size() > 1) {
                System.err.println("Frequency " + covers.ranges.get(0).end() * 4_000_000L + row);
            }
        }
    }

    private static boolean covers(List<Sensors.Range> ranges, int start, int end) {
        for (Sensors.Range range : ranges) {
            if (range.end() >= start && range.end() <= end) {
                return true;
            }
        }
        return false;
    }
}
