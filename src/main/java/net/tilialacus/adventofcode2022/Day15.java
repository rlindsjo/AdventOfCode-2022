package net.tilialacus.adventofcode2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day15 {

    public static void main(String[] args) {
        List<Sensors.Sensor> sensors = FileUtil.resourceLinesAsList("day15/sensors.txt").stream()
                .map(Sensors::parse).toList();
        Set<Integer> excluded = sensors
                .stream()
                .map(it -> it.notInRow(2000000))
                .reduce(new HashSet<>(), (a, b) -> { a.addAll(b); return a; });
        Set<Integer> beacons = sensors.stream()
                .filter(it -> it.by == 2000000)
                .map(it -> it.bx)
                .collect(Collectors.toSet());
        System.err.println("Excluded " + (excluded.size() - beacons.size()));
    }
}
