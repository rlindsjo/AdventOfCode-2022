package net.tilialacus.adventofcode2022;

import static java.lang.Integer.parseInt;

public class Assignment {
    private final int start;
    private final int end;

    public Assignment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static Assignment parseAssignment(String input) {
        String[] parts = input.split("-");

        return new Assignment(parseInt(parts[0]), parseInt(parts[1]));
    }

    public static AssignmentPair parseAssignmentPair(String input) {
        String[] parts = input.split(",");

        return new AssignmentPair(parseAssignment(parts[0]), parseAssignment(parts[1]));
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    private boolean contains(Assignment other) {
        return overlapsPoint(other.start) && overlapsPoint(other.end);
    }

    private boolean overlaps(Assignment other) {
        return overlapsPoint(other.start) || overlapsPoint(other.end);
    }

    private boolean overlapsPoint(int point) {
        return start <= point && end >= point;
    }

    public static class AssignmentPair {
        private final Assignment first;
        private final Assignment second;

        public AssignmentPair(Assignment first, Assignment second) {
            this.first = first;
            this.second = second;
        }

        public Assignment getFirst() {
            return first;
        }

        public Assignment getSecond() {
            return second;
        }

        public boolean fullyOverlaps() {
            return first.contains(second) || second.contains(first);
        }

        public boolean partiallyOverlaps() {
            return first.overlaps(second) || second.overlaps(first);
        }
    }
}
