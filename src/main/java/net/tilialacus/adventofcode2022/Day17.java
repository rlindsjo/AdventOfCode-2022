package net.tilialacus.adventofcode2022;

public class Day17 {
    public static void main(String[] args) {
        Tetris tetris = new Tetris(FileUtil.resourceLinesAsList("day17/winds.txt").get(0));
        tetris.drop(2022);
        System.err.println("Tower height " + tetris.pit.firstEmpty());
    }
}
