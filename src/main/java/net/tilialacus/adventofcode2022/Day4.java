package net.tilialacus.adventofcode2022;

import net.tilialacus.adventofcode2022.Assignment.AssignmentPair;

import java.util.stream.StreamSupport;

public class Day4 {
    public static void main(String[] args) {
        long overlapping = StreamSupport.stream(FileUtil.resourceLines("day4/assignments.txt"), false)
                .map(Assignment::parseAssignmentPair)
                .filter(AssignmentPair::fullyOverlaps)
                .count();
        System.out.println("Assignment pairs fully overlapping is %s".formatted(overlapping));

        long partially = StreamSupport.stream(FileUtil.resourceLines("day4/assignments.txt"), false)
                .map(Assignment::parseAssignmentPair)
                .filter(AssignmentPair::partiallyOverlaps)
                .count();
        System.out.println("Assignment pairs partially overlapping is %s".formatted(partially));
    }
}
