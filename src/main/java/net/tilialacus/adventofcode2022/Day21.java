package net.tilialacus.adventofcode2022;

public class Day21 {

    public static void main(String[] args) {
        Monkeys monkeys = new Monkeys();
        FileUtil.resourceLinesAsList("day21/monkeys.txt")
                .forEach(monkeys::parse);
        System.err.println("Monkey yells " + monkeys.getMonkey("root").getValue());
        System.err.println("Human should yell " + monkeys.getHumanValue());
    }
}
