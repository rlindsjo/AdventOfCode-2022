package net.tilialacus.adventofcode2022;

public class Day16 {

    public static void main(String[] args) {
        Pipes pipes = new Pipes();
        FileUtil.resourceLinesAsList("day16/pipes.txt").forEach(pipes::add);
        Pipes.Move move = pipes.getBest(30, 1);
        System.err.println("Maximum released " + move.score);
    }
}
