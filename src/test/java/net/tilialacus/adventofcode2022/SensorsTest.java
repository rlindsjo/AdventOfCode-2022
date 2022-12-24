package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.tilialacus.adventofcode2022.Sensors.reduce;
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
    void range() {
        Sensors.Sensor sensor = Sensors.parse("Sensor at x=8, y=7: closest beacon is at x=2, y=10");
        Sensors.Range range = sensor.covered(6);
        assertEquals(0, range.start());
        assertEquals(16, range.end());
    }

    @Test
    void reduced() {
        List<Sensors.Sensor> sensors = FileUtil.resourceLinesAsList("sensors_test.txt").stream()
                .map(Sensors::parse).toList();
        int result = Sensors.covered(sensors, 10);
        assertEquals(26, result);
    }
}
