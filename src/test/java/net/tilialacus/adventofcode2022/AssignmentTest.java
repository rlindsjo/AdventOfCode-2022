package net.tilialacus.adventofcode2022;

import net.tilialacus.adventofcode2022.Assignment.AssignmentPair;
import org.junit.jupiter.api.Test;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {
    @Test
    void parse() {
        Assignment assignment = Assignment.parseAssignment("2-4");

        assertEquals(2, assignment.getStart());
        assertEquals(4, assignment.getEnd());
    }

    @Test
    void parsePair() {
        AssignmentPair assignment = Assignment.parseAssignmentPair("2-4,4-7");

        assertEquals(2, assignment.getFirst().getStart());
        assertEquals(4, assignment.getFirst().getEnd());
        assertEquals(4, assignment.getSecond().getStart());
        assertEquals(7, assignment.getSecond().getEnd());
    }

    @Test
    void fileFullOverlaps() {
        assertEquals(2,
                StreamSupport.stream(FileUtil.resourceLines("assignments_test.txt"), false)
                .map(Assignment::parseAssignmentPair)
                .filter(AssignmentPair::fullyOverlaps)
                .count()
        );
    }

    @Test
    void filePartialOverlaps() {
        assertEquals(4,
                StreamSupport.stream(FileUtil.resourceLines("assignments_test.txt"), false)
                        .map(Assignment::parseAssignmentPair)
                        .filter(AssignmentPair::partiallyOverlaps)
                        .count()
        );
    }
}