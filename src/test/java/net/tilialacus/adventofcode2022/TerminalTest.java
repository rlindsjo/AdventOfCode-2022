package net.tilialacus.adventofcode2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TerminalTest {
    @Test
    void parse() {
        Terminal terminal = Terminal.parseTerminal("terminal_output_test.txt");

        Terminal.Collector collector = new Terminal.Collector(it -> it.size() <= 100000);
        terminal.visit(collector::collect);
        System.out.println("Size is: "+ collector.match.stream().mapToInt(Terminal.Folder::size).sum());
        assertEquals(95437, collector.match.stream().mapToInt(Terminal.Folder::size).sum());
    }
}