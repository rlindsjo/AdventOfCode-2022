package net.tilialacus.adventofcode2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Stacks {
    public static final Pattern PATTERN = Pattern.compile("^move (?<total>\\d+) from (?<from>\\d+) to (?<to>\\d+)$");
    private final List<Stack<Character>> stacks = new ArrayList<>();

    public Stacks(int width) {
        for (int i = 0; i < width; i++) {
            stacks.add(new Stack<>());
        }
    }

    public static Stacks parseStacks(String data) {
        String[] split = data.split("\n");
        Stacks result = new Stacks(split[0].length() / 4 + 1);
        for (int level = split.length - 2; level >= 0; level--) {
            for (int i = 0; i < result.stacks.size(); i++) {
                char c = split[level].charAt(i * 4 + 1);
                if (c != ' ') {
                    result.stacks.get(i).push(c);
                }
            }
        }
        return result;
    }

    public char get(int i) {
        return stacks.get(i).peek();
    }

    public void move(String text) {
        Matcher matcher = PATTERN.matcher(text);
        matcher.find();
        int total = Integer.parseInt(matcher.group("total"));
        int from = Integer.parseInt(matcher.group("from")) - 1;
        int to = Integer.parseInt(matcher.group("to")) - 1;
        for (int i = 0; i < total; i++) {
            stacks.get(to).push(stacks.get(from).pop());
        }
    }

    public void moveAsOne(String text) {
        Matcher matcher = PATTERN.matcher(text);
        matcher.find();
        int total = Integer.parseInt(matcher.group("total"));
        int from = Integer.parseInt(matcher.group("from")) - 1;
        int to = Integer.parseInt(matcher.group("to")) - 1;
        var lift = new Stack<Character>();
        for (int i = 0; i < total; i++) {
            lift.push(stacks.get(from).pop());
        }
        while (!lift.isEmpty()) {
            stacks.get(to).push(lift.pop());
        }
    }

    public String getTop() {
        return stacks.stream()
                .map(Stack::peek)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
