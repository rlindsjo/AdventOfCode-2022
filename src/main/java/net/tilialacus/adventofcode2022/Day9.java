package net.tilialacus.adventofcode2022;

public class Day9 {
    public static void main(String[] args) {
        Rope rope = new Rope();
        FileUtil.resourceLinesAsList("day9/rope.txt")
                .forEach(rope::move);
        System.err.println("Visited pos " + rope.getVisited().size());

        rope = new Rope(10);
        FileUtil.resourceLinesAsList("day9/rope.txt")
                .forEach(rope::move);
        System.err.println("Visited wit 10 knots pos " + rope.getVisited().size());
    }
}
