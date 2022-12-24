package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SensorsTest {

    @Test
    void parse() {
        Sensors.Sensor sensor = Sensors.parse("Sensor at x=2, y=18: closest beacon is at x=-2, y=15");
        assertEquals(2, sensor.sx);
        assertEquals(18, sensor.sy);
        assertEquals(-2, sensor.bx);
        assertEquals(15, sensor.by);
    }

    @Test
    void inRange() {
        Sensors.Sensor sensor = Sensors.parse("Sensor at x=2, y=18: closest beacon is at x=-2, y=15");
        assertTrue(sensor.noBeacon(0, 19));
    }

    @Test
    void outsideRange() {
        Sensors.Sensor sensor = Sensors.parse("Sensor at x=2, y=18: closest beacon is at x=-2, y=15");
        assertFalse(sensor.noBeacon(-2, 15));
    }

    @Test
    void not() {
        Sensors.Sensor sensor = Sensors.parse("Sensor at x=8, y=7: closest beacon is at x=2, y=10");
        Set<Integer> excluded = sensor.notInRow(6);
        assertEquals(17, excluded.size());
    }

    @Test
    void name() {
        List<Sensors.Sensor> sensors = FileUtil.resourceLinesAsList("sensors_test.txt").stream()
                .map(Sensors::parse).toList();
        Set<Integer> excluded = sensors
                .stream()
                .map(it -> it.notInRow(10))
                .reduce(new HashSet<>(), (a, b) -> { a.addAll(b); return a; });
        Set<Integer> beacons = sensors.stream()
                .filter(it -> it.by == 10)
                .map(it -> it.bx)
                .collect(Collectors.toSet());
        assertEquals(26, excluded.size() - beacons.size());
    }
}
