package net.tilialacus.adventofcode2022;

import java.util.Comparator;

public class Day7 {
    public static void main(String[] args) {
        Terminal terminal = Terminal.parseTerminal("day7/terminal.txt");

        Terminal.Collector collector = new Terminal.Collector(it -> it.size() <= 100000);
        terminal.visit(collector::collect);
        System.out.println("Size is: "+ collector.match.stream().mapToInt(Terminal.Folder::size).sum());

        int spaceToFree = 30_000_000 - (70_000_000 - terminal.root.size());
        collector = new Terminal.Collector(it -> it.size() >= spaceToFree);
        terminal.visit(collector::collect);
        System.out.println("Smallest folder to delete is: " + collector.match.stream()
                .min(Comparator.comparing(Terminal.Folder::size))
                .orElseThrow()
                .size());
    }
}
