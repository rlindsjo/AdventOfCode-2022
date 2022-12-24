package net.tilialacus.adventofcode2022;

import java.util.*;
import java.util.stream.Collectors;

import static net.tilialacus.adventofcode2022.Sensors.getReduce;
import static net.tilialacus.adventofcode2022.Sensors.reduce;

public class Day15 {

    public static void main(String[] args) {
        List<Sensors.Sensor> sensors = FileUtil.resourceLinesAsList("day15/sensors.txt").stream()
                .map(Sensors::parse).toList();
        System.err.println("Excluded " + Sensors.covered(sensors,  2000000));

        for (int row = 0; row < 4_000_000; row ++) {
            List<Sensors.Range> covers = Sensors.getReduce(sensors, row);
            if (covers.size() > 1) {
                System.err.println("Frequency " + covers.get(0).end() * 4_000_000L + row);
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
