package net.tilialacus.adventofcode2022;

public class Day2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Score playing is %s".formatted(RockPaperScissors.playAll("day2/rock_paper_scissors.txt", RockPaperScissors::play)));
        System.out.println("Score playing is %s".formatted(RockPaperScissors.playAll("day2/rock_paper_scissors.txt", RockPaperScissors::playOptimal)));
    }
}
