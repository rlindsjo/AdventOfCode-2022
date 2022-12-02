package net.tilialacus.adventofcode2022;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Score playing is %s".formatted(RockPaperScissors.playAll("day2/rock_paper_scissors.txt")));
        System.out.println("Score playing is %s".formatted(RockPaperScissors.playAllOptimal("day2/rock_paper_scissors.txt")));
    }
}
