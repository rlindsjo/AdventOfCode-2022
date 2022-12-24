package net.tilialacus.adventofcode2022;

public class Day14 {
    public static void main(String[] args) {
        HollowRock hollowRock = new HollowRock();
        FileUtil.resourceLinesAsList("day14/traces.txt")
                .forEach(hollowRock::path);
        System.err.println("Grains until reaching floor " + hollowRock.dropToFloor());

        hollowRock = new HollowRock();
        FileUtil.resourceLinesAsList("day14/traces.txt")
                .forEach(hollowRock::path);
        System.err.println("Grains until reaching ceiling " + hollowRock.dropToFull());
    }
}
