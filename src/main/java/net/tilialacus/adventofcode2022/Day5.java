package net.tilialacus.adventofcode2022;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import static java.util.function.Predicate.not;

public class Day5 {
    public static void main(String[] args) {
        Stacks stacks = Stacks.parseStacks("""
                [M]                     [N] [Z]   \s
                [F]             [R] [Z] [C] [C]   \s
                [C]     [V]     [L] [N] [G] [V]   \s
                [W]     [L]     [T] [H] [V] [F] [H]
                [T]     [T] [W] [F] [B] [P] [J] [L]
                [D] [L] [H] [J] [C] [G] [S] [R] [M]
                [L] [B] [C] [P] [S] [D] [M] [Q] [P]
                [B] [N] [J] [S] [Z] [W] [F] [W] [R]
                 1   2   3   4   5   6   7   8   9\s
                """);
        StreamSupport.stream(FileUtil.resourceLines("day5/stacks.txt"), false)
                .dropWhile(not(String::isBlank))
                .skip(1)
                .forEach(stacks::moveAsOne);

        System.err.println(stacks.getTop());
    }
}
