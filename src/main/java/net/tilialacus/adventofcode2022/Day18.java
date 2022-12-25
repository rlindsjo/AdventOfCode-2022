package net.tilialacus.adventofcode2022;

public class Day18 {

    public static void main(String[] args) {
        Droplets droplets = new Droplets();
        FileUtil.resourceLinesAsList("day18/droplets.txt")
                .forEach(droplets::parse);
        System.err.println("Total surface area " + droplets.surfaceArea());
    }
}
